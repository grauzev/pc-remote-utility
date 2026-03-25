package com.grauzev.pcremote.commands;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

/**
 * In-memory store of commands indexed by command id.
 * Loads commands on startup (dev seed for now) and provides lookup/list access.
 */

@Component
public class CommandRegistry {
	
	// === State ===
	private Map<String, Command> commandsById;
	
	// === Variables ===
	private final CommandFileLoader commandFileLoader;
	
	// === Constructor ===
	public CommandRegistry(CommandFileLoader commandFileLoader) {
		this.commandFileLoader = commandFileLoader;
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
	
	// Loads validated commands from commands.json into a new map instance
	private Map<String, Command> loadCommandsFromFile() {
		try {
			CommandFileContent fileContent = commandFileLoader.load(Path.of("data", "commands.json"));
			
			Map<String, Command> loadedCommands = new HashMap<>();
			
			for (Command command : fileContent.getCommands()) {
				loadedCommands.put(command.getId(), command);
			}
			
			return loadedCommands;
		} catch (IOException e) {
			throw new IllegalStateException("Failed to load commands from data/commands.json", e);
		}
	}
	
	// === Lifecycle ===
	@PostConstruct
	void init() {
		commandsById = loadCommandsFromFile();
	}

}
