package com.grauzev.pcremote.commands;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import tools.jackson.databind.ObjectMapper;

/**
 * Loads command definitions from a JSON file.
 * This class is responsible only for reading/parsing, not for storage
 */

@Component
public class CommandFileLoader {
	
	// === Variables ===
	private static final Set<String> SUPPORTED_COMMAND_TYPES = Set.of("shell", "app");
	private final ObjectMapper objectMapper;
	
	// === Constructor ===
	public CommandFileLoader() {
		this.objectMapper = new ObjectMapper();
	}
	
	// === Public API ===
	public CommandFileContent load(Path filePath) throws IOException {
		CommandFileContent fileContent = objectMapper.readValue(filePath.toFile(), CommandFileContent.class);
		
		validateFileContent(fileContent);
		
		for (Command command : fileContent.getCommands()) {
			validateCommand(command);
		}
		
		validateUniqueCommandIds(fileContent);
		
		return fileContent;
	}
	
	// Validates top-level command file content loaded from commands.json
	private void validateFileContent(CommandFileContent fileContent) {
		if (fileContent == null) {
			throw new IllegalArgumentException("Command file content must not be null.");
		}
		
		if (fileContent.getCommands() == null) {
			throw new IllegalArgumentException("Command list must not be null.");
		}
	}
	
	// Validates required command fields loaded from commands.json
	private void validateCommand(Command command) {
		if (command == null) {
			throw new IllegalArgumentException("Command must not be null.");
		}
		
		if (!StringUtils.hasText(command.getId())) {
			throw new IllegalArgumentException("Command id must not be blank.");
		}
		
		if (!StringUtils.hasText(command.getTitle())) {
			throw new IllegalArgumentException("Command title must not be blank.");
		}
		
		if(!StringUtils.hasText(command.getType())) {
			throw new IllegalArgumentException("Command type must not be blank.");
		}
		
		if (!SUPPORTED_COMMAND_TYPES.contains(command.getType())) {
			throw new IllegalArgumentException("Unsupported command type: " + command.getType());
		}
		
		if(!StringUtils.hasText(command.getTarget())) {
			throw new IllegalArgumentException("Command target must not be blank.");
		}
	}
	
	// Validates that command ids are unique within commands.json
	private void validateUniqueCommandIds(CommandFileContent fileContent) {
		Set<String> commandIds = new HashSet<>();
		
		for (Command command : fileContent.getCommands()) {
			if (!commandIds.add(command.getId())) {
				throw new IllegalArgumentException("Duplicate command id: " + command.getId());
			}
		}
	}

}