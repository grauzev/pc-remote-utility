package com.grauzev.pcremote.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Command definition exposed via API and used by the executor.
 * Contains a stable id, a title and a command type.
 */

public class Command {

	// === Variables ===
	private String id;
	private String title;
	private String type;
	private String target;
	
	// === Constructor ===
	@JsonCreator
	public Command(
					@JsonProperty("id") String id, 
					@JsonProperty("title") String title, 
					@JsonProperty("type") String type,
					@JsonProperty("target") String target) {
		this.id = id;
		this.title = title;
		this.type = type;
		this.target = target;
	}
	
	// === Getters ====
	
	public String getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getType() {
		return type;
	}
	
	public String getTarget() {
		return target;
	}
	
	@Override
	public String toString() {
		return "Command{id='" + id + "', title='" + title + "', type='" + type + "', target='" + target + "'}";
	}
}
