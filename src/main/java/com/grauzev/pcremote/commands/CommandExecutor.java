package com.grauzev.pcremote.commands;

import org.springframework.stereotype.Component;

/**
 * Executes commands (launch apps, run shell/program, switch display, etc.).
 * For now this is a stub: it will not run real OS actions yet.
 */

@Component
public class CommandExecutor {
	
	public void execute(Command command) {
		//STUB: real OS execution will be implemented later.
		System.out.println("Executing command id: " + command.getId());
		System.out.println("Command type: " + command.getType());
		System.out.println("Command target: " + command.getTarget());
	}

}
