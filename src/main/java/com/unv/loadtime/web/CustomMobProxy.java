package com.unv.loadtime.web;


import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;


@Component
public class CustomMobProxy {

	private BrowserMobProxy proxy;
	
	
	@Autowired
	AspireRequestFilter aspireRequestFilter;

	
	@PostConstruct
	public void checkVideo() {

		// start the proxy
		proxy = new BrowserMobProxyServer();
		proxy.start(0);
		proxy.addRequestFilter(aspireRequestFilter);
		

	}

	public BrowserMobProxy getProxy() {
		return proxy;
	}

	public void setProxy(BrowserMobProxy proxy) {
		this.proxy = proxy;
	}

}
