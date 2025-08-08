package com.Java_coder_spring_security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.security.Permission;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.nimbusds.oauth2.sdk.Role;


@Configuration
@RequiredArgsConstructor
@Slf4j
@EnableMethodSecurity
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**", "/auth/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/admin/**")
                    .hasAnyAuthority(Permission.APPOINTMENT_DELETE.name(), Permission.USER_MANAGE.name())
                .requestMatchers("/admin/**").hasRole(Role.ADMIN.name())
                .requestMatchers("/doctors/**").hasAnyRole(Role.DOCTOR.name(), Role.ADMIN.name())
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .oauth2Login(oauth2 -> oauth2
                .failureHandler((request, response, exception) -> {
                    log.error("OAuth2 login failed: {}", exception.getMessage());
                    handlerExceptionResolver.resolveException(request, response, null, exception);
                })
                .successHandler(oAuth2SuccessHandler)
            )
            .exceptionHandling(ex -> ex
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    handlerExceptionResolver.resolveException(request, response, null, accessDeniedException);
                })
            );

        return http.build();
    }
}
