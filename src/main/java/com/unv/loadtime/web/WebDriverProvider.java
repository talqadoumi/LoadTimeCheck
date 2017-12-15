//package com.unv.loadtime.web;
//public class WebDriverProvider {
//
//    private static InstanPage instance=null;
//    private WebDriver driver;
//
//
//    private InstanPage(){
//
//    }
//
//    public WebDriver openBrowser(){
//
//        driver=new FirefoxDriver();
//
//        }
//        driver.manage().window().maximize();
//        return driver;
//    }
//
//    public static InstanPage getInstance(){
//        if(instance==null){
//            instance = new InstanPage();
//        }
//        return instance;
//    }
//
//}