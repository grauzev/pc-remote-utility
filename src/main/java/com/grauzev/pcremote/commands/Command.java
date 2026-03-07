package com.grauzev.pcremote.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Command definition exposed via API and used by the executor.
 * Contains a stable id, a title and a command type.
 */

public class Command {

	private String id;
	private String title;
	private String type;
	
	// === Constructor ===
	@JsonCreator
	public Command(
					@JsonProperty("id")String id, 
					@JsonProperty("title")String title, 
					@JsonProperty("type")String type) {
		this.id = id;
		this.title = title;
		this.type = type;
	}
	
	public String getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return "Command{id='" + id + "', title='" + "', type='" + type + "'}";
	}
}
