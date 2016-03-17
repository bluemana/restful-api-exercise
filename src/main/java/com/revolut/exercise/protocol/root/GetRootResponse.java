package com.revolut.exercise.protocol.root;

import java.util.ArrayList;
import java.util.List;

import com.revolut.exercise.protocol.Link;

import io.netty.handler.codec.http.HttpMethod;

public class GetRootResponse {

	private final List<Link> links;
	
	public GetRootResponse() {
		links = new ArrayList<Link>();
		links.add(new Link("/users", "users", HttpMethod.GET));
		links.add(new Link("/transactions", "transactions", HttpMethod.GET));
	}
	
	public List<Link> getLinks() {
		return links;
	}
}
