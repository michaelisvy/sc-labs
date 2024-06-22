package com.petclinic.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        (auth) -> auth
                                .requestMatchers("/open/**").permitAll()
                                .requestMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/**").hasRole("ADMIN")
                ).httpBasic(withDefaults()).build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = User.builder()
                .username("user1")
                .password("{noop}password")
                .roles("USER")
                .build();
        UserDetails user2 = User.builder()
                .username("user2")
                .password("{noop}password")
                .roles("ADMIN", "USER")
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }

}
