package com.e9pay.common;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login");
        http.securityMatcher("/admin/**", "/")
            .authorizeHttpRequests((authorize) -> authorize
                        .shouldFilterAllDispatcherTypes(true)
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .anyRequest().authenticated());
        return http.build();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain resourceFilTerChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/static/**")
            .authorizeHttpRequests((authorize) -> authorize
                        .shouldFilterAllDispatcherTypes(true)
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .anyRequest().permitAll())
            .requestCache().disable()
            .securityContext().disable()
            .sessionManagement().disable();
        return http.build();
    }

}
