package com.example.librarybookingsystem.config;

import com.example.librarybookingsystem.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        ))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()

                        // Anyone can view books
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/books/**")
                        .permitAll()

                        // Only ADMIN can create, update and delete books
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/books/**")
                        .hasRole("ADMIN")

                        .requestMatchers(org.springframework.http.HttpMethod.PUT, "/api/books/**")
                        .hasRole("ADMIN")

                        .requestMatchers(org.springframework.http.HttpMethod.DELETE, "/api/books/**")
                        .hasRole("ADMIN")

                        // Booking APIs
                                // Admin can view all bookings
                                .requestMatchers(
                                        org.springframework.http.HttpMethod.GET,
                                        "/api/bookings"
                                )
                                .hasRole("ADMIN")

// Other booking APIs
                                .requestMatchers("/api/bookings/**").permitAll()

                                .anyRequest().authenticated()
                )


                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}