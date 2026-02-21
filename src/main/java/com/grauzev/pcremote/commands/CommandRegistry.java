package com.grauzev.pcremote.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class CommandRegistry {
	
	// === State ===
	private Map<String, Command> commandsById;
	
	// === Constructor ===
	public CommandRegistry() {
		this.commandsById = new HashMap<>();
	}
	
	
	// === Public API ===
	public void add(Command command) {
		commandsById.put(command.getId(), command);
	}
	
	// === Public API ===
	public Command getById(String id) {
		return commandsById.get(id);
	}
	
	// === Public API ===
	public List<Command> getAll() {
		return List.copyOf(commandsById.values());
	}
	
	// DEV ONLY: Temporary in-memory commands to validate API/UI wiring.
	//Remove after implementing JSON loading (commands.json)
	public void seedDevData() {
		add(new Command("display_tv", "Switch to TV", "scene"));
		add(new Command("app_steam", "Open Steam", "app"));
	}
	
	// === Lifecycle ===
	@PostConstruct
	void init() {
		//DEV ONLY: seed test commands for development.
		seedDevData();
	}

}
