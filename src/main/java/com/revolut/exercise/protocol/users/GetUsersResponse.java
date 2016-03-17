package com.revolut.exercise.protocol.users;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.revolut.exercise.core.User;
import com.revolut.exercise.protocol.Link;

import io.netty.handler.codec.http.HttpMethod;

public class GetUsersResponse {

	private final Collection<User> users;
	private final List<Link> links;
	
	public GetUsersResponse(Collection<User> users) {
		this.users = users;
		links = new ArrayList<Link>();
		links.add(new Link("/users", "create", HttpMethod.POST));
	}
	
	public Collection<User> getUsers() {
		return users;
	}
	
	public List<Link> getLinks() {
		return links;
	}
}
