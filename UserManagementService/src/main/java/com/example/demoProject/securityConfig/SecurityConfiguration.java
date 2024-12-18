package com.example.demoProject.securityConfig;

import com.example.demoProject.model.Users;
import com.example.demoProject.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    private final UserRepository userRepository;

    public SecurityConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable())
                .authorizeRequests(auth->auth

                .requestMatchers("/registerUsers").permitAll()
                        .requestMatchers("/getUserById/*").permitAll()
                        .requestMatchers("/getUserByName/*").permitAll()
                .anyRequest().authenticated())
                        .httpBasic(withDefaults());
        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Users user = userRepository.findByUserName(username);
            if (user != null) {
                return User.builder()
                        .username(user.getUserName())
                        .password("{noop}" + user.getPassword())

                        .build();
            } else {
                throw new UsernameNotFoundException("User not found");
            }
        };
    }


}
