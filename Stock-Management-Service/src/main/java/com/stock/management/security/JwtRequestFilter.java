package com.stock.management.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtRequestFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public org.springframework.security.core.Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws BadCredentialsException {
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, password);
			return authenticationManager.authenticate(auth);
		} catch (Exception e) {
			throw new BadCredentialsException("Invalid username or password");
		}
	}

	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		UserDetails userDetails = userDetailsService.loadUserByUsername(authResult.getUsername());
		String jwt = jwtUtil.generateToken(userDetails);
		response.addHeader("Authorization", "Bearer " + jwt);
	}
}
