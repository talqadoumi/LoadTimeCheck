package com.unv.loadtime.controller;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.unv.loadtime.*;
import com.unv.loadtime.web.AspireRequestFilter;
import com.unv.loadtime.web.CustomMobProxy;

import net.lightbody.bmp.client.ClientUtil;

@RestController
public class VideoController {
	@Autowired
	VideoServices videoService; // Service which will do all data retrieval/manipulation work

	@Autowired
	AspireRequestFilter aspireRequestFilter;

	@Autowired
	CustomMobProxy proxyHolder;

	
	@PostMapping("/api/v1/videoloadtime/")
	public String checkVideoLoadTime(@RequestBody Video video)

	{

		System.err.println(video.getFrontEndLink());
		
		CountDownLatch latch = new CountDownLatch(1);

		FilterResult result = new FilterResult();

		aspireRequestFilter.addListener(filterProv -> {

			if (filterProv.getMessageInfo().getOriginalUrl().contains("fusionvod-a.akamaized.net")) {

				if (filterProv.getMessageInfo().getOriginalUrl().contains(".ts") && !result.videoStarted) {
					result.videoStarted = true;
					System.err.println(filterProv.getMessageInfo().getOriginalUrl());

				}
			}
			if (filterProv.getMessageInfo().getOriginalUrl().contains("2mdn.net")) {
				if (filterProv.getMessageInfo().getOriginalUrl().contains("videoplayback") && !result.adsAppeared) {
					result.adsAppeared = true;

				}
			}

			return result.videoStarted && result.adsAppeared;
		}, (_passed, exc) -> {
			result.passed = _passed;
			result.ex = exc;
			latch.countDown();
		});

		startCheckVideo(video.getFrontEndLink());

		try {
			latch.await(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			// TODO: handle exception

			e.printStackTrace();
			System.err.println(e);
			return "Time out";
		}

		if (result.passed) {
			return "Passed";
		} else {
			return "Failed";
		}

	}

	private void startCheckVideo(String videoUrl) {
        System.out.println("VideoController.startCheckVideo()");
		Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxyHolder.getProxy());

		// configure it as a desired capability
		System.setProperty("webdriver.chrome.driver",
				"/Users/tariqalqadoumi/Downloads/Website-springbootrestservices-all-examples/src/test/java/chromedriver");
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

		ChromeDriver driver = new ChromeDriver(capabilities);
		driver.get(videoUrl);
		
	}

	public class FilterResult {

		public boolean passed = false;
		public boolean videoStarted = false;
		public boolean adsAppeared = false;
		public Throwable ex;
	}

}
