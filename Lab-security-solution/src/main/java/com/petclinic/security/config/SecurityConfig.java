package com.petclinic.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

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

    @Bean @Profile("inmemory")
    public InMemoryUserDetailsManager userDetailsService() {
        return new InMemoryUserDetailsManager(getUser1(), getUser2());
    }

    @Bean @Profile("database")
    UserDetailsManager users(DataSource dataSource) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource());
        users.createUser(getUser1());
        users.createUser(getUser2());
        return users;
    }
    @Bean @Profile("database")
    DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION).build();
    }



    private UserDetails getUser1() {
        return User.builder()
                .username("user1")
                .password("{noop}password")
                .roles("USER")
                .build();
    }

    private UserDetails getUser2() {
        return User.builder()
                .username("user2")
                .password("{noop}password")
                .roles("USER","ADMIN")
                .build();
    }

}
