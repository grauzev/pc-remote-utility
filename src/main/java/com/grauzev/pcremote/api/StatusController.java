package com.grauzev.pcremote.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Basic service status endpoint (health-like check).
 * Used to verify that the backend is running and reachable.
 */

@RestController
public class StatusController {
	@GetMapping("/api/status")
	public String status() {
		return "ok";
	}
	
}
