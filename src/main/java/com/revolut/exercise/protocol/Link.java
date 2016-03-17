package com.revolut.exercise.protocol;

import io.netty.handler.codec.http.HttpMethod;

public class Link {

	private transient final String id;
	private final String uri;
	private final String rel;
	private final HttpMethod method;
	
	public Link(String uri, String rel, HttpMethod method) {
		int idx = uri.indexOf('/', 1);
		id = method.name() + " " + (idx > 0 ? uri.substring(0, idx) : uri);
		this.uri = uri;
		this.rel = rel;
		this.method = method;
	}
	
	public String getId() {
		return id;
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
	
	public int getResourceId() {
		int id = -1;
		int idx = uri.indexOf('/', 1);
		if (idx > 0) {
			id = Integer.parseInt(uri.substring(idx + 1));
		}
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Link) {
			result = ((Link) obj).id.equals(id);
		}
		return result;
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	@Override
	public String toString() {
		return String.format("link{id=%s;uri=%s;rel=%s;method=%s}", id, uri, rel, method.name());
	}
}
