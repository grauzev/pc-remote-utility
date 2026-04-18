package com.grauzev.pcremote.api;

/**
 * Runtime-safe command response for API clients
 */
public class RuntimeCommandResponse {
	
	// === Variables ===
	private final String id;
	private final String title;
	private final String type;
	
	// === Constructor ===
	public RuntimeCommandResponse(String id, String title, String type) {
		this.id = id;
		this.title = title;
		this.type = type;
	}
	
	// === Getters ===
	public String getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getType() {
		return type;
	}

}
