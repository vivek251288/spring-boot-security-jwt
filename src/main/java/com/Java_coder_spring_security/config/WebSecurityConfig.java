package com.Java_coder_spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
	
	private final PasswordEncoder passwordEncoder;

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/public/**").permitAll()
	                .requestMatchers("/admin/**").authenticated()
	            )
	            .formLogin(Customizer.withDefaults());

	        return http.build();
	    }
	    
	    @Bean
	    public UserDetailsService userDetailsService() {
	        UserDetails user1 = User.withUsername("admin")
	            .password(passwordEncoder.encode("pass"))
	            .roles("ADMIN")
	            .build();

	        UserDetails user2 = User.withUsername("patient")
	            .password(passwordEncoder.encode("pass"))
	            .roles("PATIENT")
	            .build();

	        return new InMemoryUserDetailsManager(user1, user2);
	    }
	
	}



