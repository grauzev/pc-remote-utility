package com.grauzev.pcremote.api;

/**
 * DTO for the /execute endpoint response.
 * Returned as JSON to clients (Web UI, Siri/Shortcuts).
 */

public class ExecuteResponse {
	
	// === Variables ===
	private String status;
	private String commandId;
	private String message;
	
	// === Constructor ===
	public ExecuteResponse(String status, String commandId, String message) {
		this.status = status;
		this.commandId = commandId;
		this.message = message;
	}
	
	// === Getters ===
	public String getStatus() {
		return status;
	}
	
	public String getCommandId() {
		return commandId;
	}
	
	public String getMessage() {
		return message;
	}

}
