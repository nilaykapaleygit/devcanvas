package com.devcanvas.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		 http
         .csrf(csrf -> csrf.disable())

         .cors(cors -> {})

         .authorizeHttpRequests(auth -> auth

             // Angular CORS preflight
             .requestMatchers(
                 HttpMethod.OPTIONS,
                 "/**"
             ).permitAll()

             // Public Resume APIs
             .requestMatchers(
            		    "/api/resumes/public",
            		    "/api/resumes/public/view"
              ).permitAll()

             // Public Project READ APIs
             .requestMatchers(
                 HttpMethod.GET,
                 "/api/projects",
                 "/api/projects/*"
             ).permitAll()

             // Public Artwork APIs
             .requestMatchers(
                 "/api/artworks/**"
             ).permitAll()

             // Public Profile APIs
             .requestMatchers(
                 "/api/profile/**"
             ).permitAll()

             // Public Contact APIs
             .requestMatchers(
                 "/api/contact/**"
             ).permitAll()

             // Swagger
             .requestMatchers(
                 "/swagger-ui/**",
                 "/v3/api-docs/**"
             ).permitAll()

             // Admin Resume APIs
             .requestMatchers(
                 "/api/resumes/upload",
                 "/api/resumes/*/select",
                 "/api/resumes/*"
             ).hasRole("ADMIN")

             // Admin Project APIs
             .requestMatchers(
                 HttpMethod.POST,
                 "/api/projects"
             ).hasRole("ADMIN")

             .requestMatchers(
                 HttpMethod.PUT,
                 "/api/projects/*"
             ).hasRole("ADMIN")

             .requestMatchers(
                 HttpMethod.DELETE,
                 "/api/projects/*"
             ).hasRole("ADMIN")

             // Everything else requires authentication
             .anyRequest().authenticated()
         )

         .httpBasic(httpBasic -> {});

     return http.build();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {

	    CorsConfiguration configuration = new CorsConfiguration();

	    configuration.addAllowedOrigin("http://localhost:4200");
	    configuration.addAllowedHeader("*");
	    configuration.addAllowedMethod("*");

	    UrlBasedCorsConfigurationSource source =
	            new UrlBasedCorsConfigurationSource();

	    source.registerCorsConfiguration("/**", configuration);

	    return source;
	}
	
	
}
