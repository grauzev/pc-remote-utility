package com.grauzev.pcremote.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grauzev.pcremote.commands.Command;
import com.grauzev.pcremote.commands.CommandExecutor;
import com.grauzev.pcremote.commands.CommandRegistry;

/**
 * HTTP API for command listing and execution endpoints.
 * Web remote and Siri/Shortcuts will call these endpoints.
 */

@RestController
public class CommandsController {
	
	// === Variables ===
	private final CommandRegistry commandRegistry;
	private final CommandExecutor commandExecutor;
	
	// === Constructor ===
	public CommandsController(CommandRegistry commandRegistry, CommandExecutor commandExecutor) {
		this.commandRegistry = commandRegistry;
		this.commandExecutor = commandExecutor;
	}
	
	@GetMapping("/api/commands")
	public List<Command> getCommands() {
		return commandRegistry.getAll();
	}
	
	@PostMapping("/api/commands/{id}/execute")
	public ResponseEntity<ExecuteResponse> execute(@PathVariable String id) {
		Command command = commandRegistry.getById(id);
		if (command == null) {
			return ResponseEntity.status(404).body(new ExecuteResponse("not_found", id, "command not found"));
		}
		
		commandExecutor.execute(command);
		return ResponseEntity.ok(new ExecuteResponse("ok", id, "executed"));
	}

}
