package com.grauzev.pcremote.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandRegistry {
	
	private Map<String, Command> commandsById;
	
	public CommandRegistry() {
		this.commandsById = new HashMap<>();
	}
	
	public void add(Command command) {
		commandsById.put(command.getId(), command);
	}
	
	public Command getById(String id) {
		return commandsById.get(id);
	}
	
	public List<Command> getAll() {
		return List.copyOf(commandsById.values());
	}

}
