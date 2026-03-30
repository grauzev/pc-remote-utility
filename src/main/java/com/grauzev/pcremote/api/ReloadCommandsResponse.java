package com.grauzev.pcremote.api;

/**
 * DTO for the commands reload endpoint response.
 * Returned as JSON to management clients.
 */

public class ReloadCommandsResponse {
	
	// === Variables ===
	private String status;
	private String message;
	
	// === Constructor ===
	public ReloadCommandsResponse(String status, String message) {
		this.status = status;
		this.message = message;
	}
	
	// === Getters ===
	public String getStatus() {
		return status;
	}
	
	public String getMessage() {
		return message;
	}

}
