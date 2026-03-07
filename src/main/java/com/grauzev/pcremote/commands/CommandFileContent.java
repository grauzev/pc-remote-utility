package com.grauzev.pcremote.commands;

import java.util.List;

/**
 * DTO representing the full commands.json file content.
 * Contains schema metadata and list of command definitions.
 */

public class CommandFileContent {
	
	// === Variables===
	private int schemaVersion;
	private List<Command> commands;
	
	public int getSchemaVersion() {
		return schemaVersion;
	}
	
	public List<Command> getCommands() {
		return commands;
	}

}
