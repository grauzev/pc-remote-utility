package com.grauzev.pcremote.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grauzev.pcremote.commands.Command;
import com.grauzev.pcremote.commands.CommandRegistry;

@RestController
public class CommandsController {
	
	private final CommandRegistry commandRegistry;
	
	public CommandsController(CommandRegistry commandRegistry) {
		this.commandRegistry = commandRegistry;
	}
	
	@GetMapping("/api/commands")
	public List<Command> getCommands() {
		return commandRegistry.getAll();
	}

}
