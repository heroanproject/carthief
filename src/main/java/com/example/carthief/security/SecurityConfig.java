package com.example.carthief.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    public static final String API = "/api/**";

    @Bean
    @Order(1)
    public SecurityFilterChain filterChainForRestApi(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .securityMatcher(API)
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/api/messages").permitAll()
                .requestMatchers(HttpMethod.POST, API).hasAuthority(Role.ADMIN.getAuthority())
                .requestMatchers(HttpMethod.GET, "/api/dealers/**").hasAnyAuthority(Role.USER.getAuthority(), Role.ADMIN.getAuthority())
                .requestMatchers(HttpMethod.GET, "/api/cars/**").hasAnyAuthority(Role.USER.getAuthority(), Role.ADMIN.getAuthority())
                .requestMatchers(HttpMethod.GET, "/api/persons/**").hasAnyAuthority(Role.USER.getAuthority(), Role.ADMIN.getAuthority())
                .requestMatchers(HttpMethod.GET, API).hasAuthority(Role.ADMIN.getAuthority())
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .ignoringRequestMatchers("/register")
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/error").permitAll()
                .requestMatchers(HttpMethod.POST, "/register").permitAll()
                .requestMatchers("/carthief").permitAll()
                .requestMatchers("/*.css").permitAll()
                .requestMatchers("/search").permitAll()
                .requestMatchers("/save").authenticated()
                .requestMatchers("/savedealer").authenticated()
                .anyRequest().denyAll()
                .and()
                .oauth2Login()
                .defaultSuccessUrl("/carthief")
                .and()
                .logout(logout -> logout.logoutSuccessUrl("/carthief"));

        return httpSecurity.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
