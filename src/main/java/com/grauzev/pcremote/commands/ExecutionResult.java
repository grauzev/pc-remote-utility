package com.grauzev.pcremote.commands;

/**
 * Result of command execution.
 * Will be used to report success or failure back to the API layer.
 */

public class ExecutionResult {
	
	// === Variables ===
	private boolean success;
	private String message;
	
	// === Constructor ===
	public ExecutionResult(boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	
	// === Getters ===
	public boolean isSuccess() {
		return success;
	}
	
	public String getMessage() {
		return message;
	}

}
