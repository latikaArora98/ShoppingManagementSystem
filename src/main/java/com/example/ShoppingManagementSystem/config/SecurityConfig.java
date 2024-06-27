package com.example.ShoppingManagementSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.ShoppingManagementSystem.service.MyUserDetailsService;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
	public SecurityFilterChain  configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests(authorizeRequests->authorizeRequests
		.requestMatchers("/api/auth/**").permitAll()
		.requestMatchers("/api/products/**").hasAnyRole("MANAGER","ADMIN")
		.requestMatchers("/api/orders/**").hasAnyRole("USER","ADMIN")
		.requestMatchers("/api/admin/**").hasRole("ADMIN")
		.anyRequest().authenticated()
		)
		.csrf().disable()
		.formLogin().disable()
		.httpBasic().disable();
		
		//http.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
		
	}
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*"); // Replace with specific origin if needed
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public MyUserDetailsService myUserDetailsService(){
		return new MyUserDetailsService();
	}

}
