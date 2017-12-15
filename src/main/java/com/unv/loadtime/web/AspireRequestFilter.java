package com.unv.loadtime.web;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import org.springframework.stereotype.Component;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;

@Component
public class AspireRequestFilter implements RequestFilter {

	List<FilterListener> allListners = new ArrayList<>();
	@Override
	public HttpResponse filterRequest(HttpRequest request, HttpMessageContents contents, HttpMessageInfo messageInfo) {

		FilterRequestProvider filterReqPro = new FilterRequestProvider(request,contents,messageInfo);
		
		(new ArrayList<FilterListener>(allListners))
		
			.stream()
			.filter(listner->listner.getPredicate().test(filterReqPro))
			.forEach(listner->{
				listner.consumer.accept(true, null);
				allListners.remove(listner);
			});
		
		return null;
	}
	
	public static class FilterRequestProvider{
		HttpRequest request;
		HttpMessageContents contents;
		HttpMessageInfo messageInfo;
		
		public FilterRequestProvider(HttpRequest request,HttpMessageContents contents,HttpMessageInfo messageInfo) {
			this.request =request;
			this.contents=contents;
			this.messageInfo=messageInfo;
		}

		public HttpRequest getRequest() {
			return request;
		}

		public HttpMessageContents getContents() {
			return contents;
		}

		public HttpMessageInfo getMessageInfo() {
			return messageInfo;
		}

		public void setRequest(HttpRequest request) {
			this.request = request;
		}

		public void setContents(HttpMessageContents contents) {
			this.contents = contents;
		}

		public void setMessageInfo(HttpMessageInfo messageInfo) {
			this.messageInfo = messageInfo;
		}
	}
	
	public static class FilterListener{
		


		private BiConsumer<Boolean, Throwable> consumer;
		private Predicate<FilterRequestProvider> predicate;

		public FilterListener(Predicate<FilterRequestProvider> predicate,BiConsumer<Boolean,Throwable> consumer) {
			this.predicate = predicate;
			this.consumer=consumer;
			
		}
		


		public BiConsumer<Boolean, Throwable> getConsumer() {
			return consumer;
		}

		public Predicate<FilterRequestProvider> getPredicate() {
			return predicate;
		}



		public void setConsumer(BiConsumer<Boolean, Throwable> consumer) {
			this.consumer = consumer;
		}

		public void setPredicate(Predicate<FilterRequestProvider> predicate) {
			this.predicate = predicate;
		}
	}

	
	public void addListener(Predicate<FilterRequestProvider> predicate,BiConsumer<Boolean,Throwable> consumer) {
		allListners.add(new FilterListener(predicate, consumer));
	}

}
