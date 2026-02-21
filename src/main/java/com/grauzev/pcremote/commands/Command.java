package com.grauzev.pcremote.commands;

public class Command {

	private String id;
	private String title;
	private String type;
	
	public Command(String id, String title, String type) {
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
