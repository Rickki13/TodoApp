//package com.example.task.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//
//import java.util.List;
//
//import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration {
//
//    @Autowired
//    private JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(request -> request
//                        .requestMatchers("/auth/signup", "/auth/signin").permitAll()
//                        .anyRequest().authenticated())
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(STATELESS));
//
//        return http.build();
//    }
////    private final JwtAuthenticationFilter jwtAuthenticationFilter;
////
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http.csrf(AbstractHttpConfigurer::disable)
////                // Отключение CORS (разрешение запросов со всех доменов)
////                .cors(cors -> cors.configurationSource(request -> {
////                    var corsConfiguration = new CorsConfiguration();
////                    corsConfiguration.setAllowedOriginPatterns(List.of("*"));
////                    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
////                    corsConfiguration.setAllowedHeaders(List.of("*"));
////                    corsConfiguration.setAllowCredentials(true);
////                    return corsConfiguration;
////                }))
////                .authorizeHttpRequests(request -> request
////                        .requestMatchers("/tasks/active", "/tasks/period").permitAll()
////                        .requestMatchers("/tasks/create", "/admin/**").hasRole("ADMIN")
////                        .requestMatchers("/tasks/create", "/admin/**").hasRole("USER")
////                        .anyRequest().permitAll())
////                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
////                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
////                .headers(httpSecurityHeadersConfigurer -> {
////                    httpSecurityHeadersConfigurer.
////                            frameOptions(HeadersConfigurer.FrameOptionsConfig::disable);
////                });
////
////        return http.build();
////    }
//}