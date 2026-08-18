package com.example.librarybookingsystem.config;

import com.example.librarybookingsystem.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                // Disable CSRF because this is a REST API
                .csrf(csrf -> csrf.disable())

                // Enable CORS
                .cors(cors ->
                        cors.configurationSource(corsConfigurationSource())
                )

                // JWT authentication = stateless
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(auth -> auth

                        // =========================
                        // AUTHENTICATION
                        // =========================
                        .requestMatchers("/api/auth/**")
                        .permitAll()

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/bookings/*/return"
                        )
                        .permitAll()

                        // =========================
                        // USER REGISTRATION
                        // =========================
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/users"
                        )
                        .permitAll()

                        // =========================
                        // BOOKS
                        // =========================

                        // Anyone can view books
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/books/**"
                        )
                        .permitAll()

                        // Only ADMIN can create books
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/books/**"
                        )
                        .hasRole("ADMIN")

                        // Only ADMIN can update books
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/books/**"
                        )
                        .hasRole("ADMIN")

                        // Only ADMIN can delete books
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/books/**"
                        )
                        .hasRole("ADMIN")

                        // =========================
                        // BOOKINGS
                        // =========================

                        // Logged-in USER/ADMIN can create booking
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/bookings"
                        )
                        .permitAll()

                        // Admin can view ALL bookings
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/bookings"
                        )
                        .hasRole("ADMIN")

                        // Logged-in user can view reservation history
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/bookings/user/**"
                        )
                        .authenticated()

                        // Get booking by ID
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/bookings/**"
                        )
                        .authenticated()

                        // Update booking
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/bookings/**"
                        )
                        .permitAll()

                        // Delete booking
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/bookings/**"
                        )
                        .authenticated()

                        // =========================
                        // EVERYTHING ELSE
                        // =========================
                        .anyRequest()
                        .authenticated()
                )

                // JWT filter
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    // =========================
    // CORS CONFIGURATION
    // =========================
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration =
                new CorsConfiguration();

        configuration.setAllowedOrigins(
                List.of("http://localhost:4200")
        );

        configuration.setAllowedMethods(
                List.of(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "OPTIONS"
                )
        );

        configuration.setAllowedHeaders(
                List.of("*")
        );

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration(
                "/**",
                configuration
        );

        return source;
    }

    // =========================
    // PASSWORD ENCODER
    // =========================
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}