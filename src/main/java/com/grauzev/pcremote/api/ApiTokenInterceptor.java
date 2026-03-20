package com.grauzev.pcremote.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Intercepts API requests and validates the configured API token.
 * Requests without a valid token will be rejected before reaching controllers.
 */

@Component
public class ApiTokenInterceptor implements HandlerInterceptor {
	
	// === Variables ===
	@Value("${app.api.token}")
	private String apiToken;
	
	// === Public API ===
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		String requestToken = request.getHeader("X-API-Token");
		boolean tokenMatches = apiToken.equals(requestToken);
		if (!tokenMatches) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid API token");
			return false;
		}
		
		return true;
	}

}
