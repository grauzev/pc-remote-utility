package com.grauzev.pcremote.commands;

import java.io.IOException;

import org.springframework.stereotype.Component;

/**
 * Executes commands (launch apps, run shell/program, switch display, etc.).
 * For now this is a stub: it will not run real OS actions yet.
 */

@Component
public class CommandExecutor {
	
	public ExecutionResult execute(Command command) {
		try {
			if ("shell".equals(command.getType())) {
				new ProcessBuilder("cmd.exe", "/c", command.getTarget()).start();
				System.out.println("Started shell command: " + command.getTarget());
				return new ExecutionResult(true, "executed");
			}
			
			if ("app".equals(command.getType())) {
				new ProcessBuilder("cmd.exe", "/c", "start", "", command.getTarget()).start();
				System.out.println("Started app command: " + command.getTarget());
				return new ExecutionResult(true, "executed");
			}
			
			return new ExecutionResult(false, "unsupported command type");
		} catch (IOException e) {
			return new ExecutionResult(false, "failed to execute command");
		}
	}

}
