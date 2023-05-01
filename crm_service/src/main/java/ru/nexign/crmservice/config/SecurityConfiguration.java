package ru.nexign.crmservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/manager/**").hasRole("MANAGER")
                .requestMatchers("/abonent/**").hasRole("ABONENT")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .logout().logoutSuccessUrl("/");
        return http.build();
    }
}
