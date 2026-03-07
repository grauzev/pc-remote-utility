package com.grauzev.pcremote.commands;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.stereotype.Component;

import tools.jackson.databind.ObjectMapper;

/**
 * Loads command definitions from a JSON file.
 * This class is responsible only for reading/parsing, not for storage
 */

@Component
public class CommandFileLoader {
	
	// === Variables ===
	private final ObjectMapper objectMapper;
	
	// === Constructor ===
	public CommandFileLoader() {
		this.objectMapper = new ObjectMapper();
	}
	
	// === Public API ===
	public CommandFileContent load(Path filePath) throws IOException {
		return objectMapper.readValue(filePath.toFile(), CommandFileContent.class);
	}

}