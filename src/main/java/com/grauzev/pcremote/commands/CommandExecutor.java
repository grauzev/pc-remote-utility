package com.grauzev.pcremote.commands;

import java.io.IOException;

import org.springframework.stereotype.Component;

/**
 * Executes commands (launch apps, run shell/program, switch display, etc.).
 * For now this is a stub: it will not run real OS actions yet.
 */

@Component
public class CommandExecutor {
	
	public void execute(Command command) {
		if (!"shell".equals(command.getType())) {
			System.out.println("Skipping non-shell command: " + command.getId());
			return;
		}
		
		try {
			new ProcessBuilder("cmd.exe", "/c", command.getTarget()).start();
			System.out.println("Started shellcommand: " + command.getTarget());
		} catch (IOException e) {
			throw new IllegalStateException("Failed to execute shell command: " + command.getTarget(), e);
		}
	}

}
