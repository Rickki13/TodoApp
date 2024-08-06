//package com.example.task.config;
//
//import com.example.task.model.User;
//import com.example.task.service.JwtService;
//import com.example.task.service.UserService;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.NonNull;
//import lombok.RequiredArgsConstructor;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Collections;
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JwtService jwtService;
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//
//        String authHeader = request.getHeader("Authorization");
//        String token = null;
//        String username = null;
//
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            token = authHeader.substring(7);
//            username = jwtService.extractUsername(token);
//        }
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = userService.getUserData(username, token);
//            if (jwtService.isTokenValid(token, userDetails)) {
//                UsernamePasswordAuthenticationToken authenticationToken =
//                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
//}
//
//// ВТОРОЙ ВАРИАНТ
////    private final JwtService jwtService;
////    private final UserService userService;
////
////    @Override
////    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
////        String authHeader = request.getHeader("Authorization");
////        if (StringUtils.isEmpty(authHeader) || !authHeader.startsWith("Bearer ")) {
////            filterChain.doFilter(request, response);
////            return;
////        }
////
////        String jwt = authHeader.substring(7);
////        String username = jwtService.extractUsername(jwt);
////
////        if (StringUtils.isNotEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
////            UserDetails userDetails = userService.loadUserByUsername(username, jwt); // передача токена
////            if (jwtService.isTokenValid(jwt, userDetails)) {
////                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
////                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////                SecurityContextHolder.getContext().setAuthentication(authToken);
////            }
////        }
////
////        filterChain.doFilter(request, response);
////    }
//
//
//
//
//// ПЕРВЫЙ ВАРИНАТ
////@Component
////@RequiredArgsConstructor
////public class JwtAuthenticationFilter extends OncePerRequestFilter {
////    public static final String BEARER_PREFIX = "Bearer ";
////    public static final String HEADER_NAME = "Authorization";
////    private final JwtService jwtService;
////    private final UserService userService;
////
////    @Override
////    protected void doFilterInternal(
////            @NonNull HttpServletRequest request,
////            @NonNull HttpServletResponse response,
////            @NonNull FilterChain filterChain
////    ) throws ServletException, IOException {
////
////        var authHeader = request.getHeader(HEADER_NAME);
////        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, BEARER_PREFIX)) {
////            filterChain.doFilter(request, response);
////            return;
////        }
////
////        String jwt = authHeader.substring(BEARER_PREFIX.length());
////        String username = jwtService.extractUsername(jwt);
////
////        if (StringUtils.isNotEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
////            User user = userService.getUserData(username, jwt);
////
////            // Если токен валиден, то аутентифицируем пользователя
////            if (jwtService.isTokenValid(jwt, user)) {
////                SecurityContext context = SecurityContextHolder.createEmptyContext();
////
////                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
////                        user,
////                        null,
////                        user.getAuthorities()
////                );
////
////                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////                context.setAuthentication(authToken);
////                SecurityContextHolder.setContext(context);
////            }
////        }
////        filterChain.doFilter(request, response);
////    }
////}