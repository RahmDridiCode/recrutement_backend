package org.example.recrutement.config;

import org.example.recrutement.filters.JWTrequestfilter;
import org.example.recrutement.services.impl.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JWTrequestfilter jwtfilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())   // Désactiver CSRF
                .cors(cors -> {
                    cors.configurationSource(request -> {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(List.of("http://localhost:4200"));
                        config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
                        config.setAllowedHeaders(List.of("*"));
                        config.setAllowCredentials(true);
                        return config;
                    });
                })

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register", "/offre/list/", "/public/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        http.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
