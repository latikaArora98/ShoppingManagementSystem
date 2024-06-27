package com.example.ShoppingManagementSystem.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.ShoppingManagementSystem.service.MyUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	MyUserDetailsService uds;
	
	@Autowired
	JwtUtil jwtutil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		final String authHeader = request.getHeader("Authorization");
		String username= null;
		String jwt = null;
		
		if(authHeader!=null && authHeader.startsWith("Bearer")) {
			jwt = authHeader.substring(7);
			username  = jwtutil.extractUsername(jwt);
		}
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userdetails = uds.loadUserByUsername(username);
			
			if(jwtutil.validateToken(jwt, userdetails.getUsername())) {
				UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(userdetails, null, userdetails.getAuthorities());
			upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(upat);
			}
		}
		filterChain.doFilter(request, response);
	}

}
