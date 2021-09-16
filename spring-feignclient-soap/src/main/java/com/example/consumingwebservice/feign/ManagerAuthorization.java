package com.example.consumingwebservice.feign;

import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class ManagerAuthorization {

	public String getSoapBasicAuthorization() {
		String auth = Base64.getEncoder().encodeToString(("soaTester" + ":" + "claro#123").getBytes());
		return "Basic " + auth;
	}
}
