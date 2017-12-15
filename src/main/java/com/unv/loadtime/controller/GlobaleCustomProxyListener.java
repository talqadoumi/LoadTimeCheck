package com.unv.loadtime.controller;
//<<<<<<< HEAD
////package com.univision.loadtime.zap;
////
////import java.util.HashMap;
////import java.util.Map;
////
////import org.parosproxy.paros.core.proxy.ProxyListener;
////import org.parosproxy.paros.network.HttpMessage;
////
////public class GlobaleCustomProxyListener implements ProxyListener {
////
////	Map<String, ProxyListener> listenersMap = new HashMap<>();
////	private String defaultTageName = "Default_user-agent";
////
////	private static GlobaleCustomProxyListener instance = null;
////
////	private GlobaleCustomProxyListener() {
////
////	}
////
////	public static synchronized GlobaleCustomProxyListener getInstance() 
////	{
////		if (instance == null) {
////			instance = new GlobaleCustomProxyListener();
////		}
////		return instance;
////	}
////
////	public void addListner(ProxyListener listener, String tageName) {
////		listenersMap.put(tageName, listener);
////	}
////
////	@Override
////	public int getArrangeableListenerOrder() {
////		return 0;
////	}
////
////	@Override
////	public boolean onHttpRequestSend(HttpMessage message) {
////
////		String tageName = getTageName(message);
////		ProxyListener targetProxy = listenersMap.get(tageName);
////		if (targetProxy == null) {
////			return true;
////		}
////		return targetProxy.onHttpRequestSend(message);
////	}
////
////	@Override
////	public boolean onHttpResponseReceive(HttpMessage message) {
////
////		String tageName = getTageName(message);
////		ProxyListener targetProxy = listenersMap.get(tageName);
////		if (targetProxy == null) {
////			return true;
////		}
////		return targetProxy.onHttpResponseReceive(message);
////	}
////
////	private String getTageName(HttpMessage message) {
////		// get from user Agent
////		String tagName = message.getRequestHeader().getHeader("User-Agent");
////		return tagName != null ? tagName : defaultTageName;
////	}
////}
//=======
//package com.univision.loadtime.zap;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.parosproxy.paros.core.proxy.ProxyListener;
//import org.parosproxy.paros.network.HttpMessage;
//
//public class GlobaleCustomProxyListener implements ProxyListener {
//
//	Map<String, ProxyListener> listenersMap = new HashMap<>();
//	private String defaultTageName = "Default_user-agent";
//
//	private static GlobaleCustomProxyListener instance = null;
//
//	private GlobaleCustomProxyListener() {
//
//	}
//
//	public static synchronized GlobaleCustomProxyListener getInstance() 
//	{
//		if (instance == null) {
//			instance = new GlobaleCustomProxyListener();
//		}
//		return instance;
//	}
//
//	public void addListner(ProxyListener listener, String tageName) {
//		listenersMap.put(tageName, listener);
//	}
//
//	@Override
//	public int getArrangeableListenerOrder() {
//		return 0;
//	}
//
//	@Override
//	public boolean onHttpRequestSend(HttpMessage message) {
//
//		String tageName = getTageName(message);
//		ProxyListener targetProxy = listenersMap.get(tageName);
//		if (targetProxy == null) {
//			return true;
//		}
//		return targetProxy.onHttpRequestSend(message);
//	}
//
//	@Override
//	public boolean onHttpResponseReceive(HttpMessage message) {
//
//		String tageName = getTageName(message);
//		ProxyListener targetProxy = listenersMap.get(tageName);
//		if (targetProxy == null) {
//			return true;
//		}
//		return targetProxy.onHttpResponseReceive(message);
//	}
//
//	private String getTageName(HttpMessage message) {
//		// get from user Agent
//		String tagName = message.getRequestHeader().getHeader("User-Agent");
//		return tagName != null ? tagName : defaultTageName;
//	}
//}
//>>>>>>> develop
