package com.revolut.exercise.protocol;

import java.util.Objects;

import io.netty.handler.codec.http.HttpMethod;

public class Link {

	private final String uri;
	private final String rel;
	private final HttpMethod method;
	
	public Link(String uri, String rel, HttpMethod method) {
		this.uri = uri;
		this.rel = rel;
		this.method = method;
	}
	
	public String getUri() {
		return uri;
	}
	
	public String getRel() {
		return rel;
	}
	
	public HttpMethod getMethod() {
		return method;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Link) {
			Link link = (Link) obj;
			result = link.uri.equals(uri) && link.method.equals(method);
		}
		return result;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(uri, method.name());
	}
}
