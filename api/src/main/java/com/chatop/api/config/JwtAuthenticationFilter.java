package com.chatop.api.config;

import com.chatop.api.entity.User;
import com.chatop.api.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final UserRepository userRepository;
    private final SecretKey jwtKey;

    public JwtAuthenticationFilter(UserRepository userRepository,
                                   @Value("${jwt.secret}") String jwtSecret) {
        this.userRepository = userRepository;
        this.jwtKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authHeader.substring(7);

        try {
            Claims claims = parseToken(token);
            String subject = claims.getSubject();

            if (subject == null) {
                logger.warn("JWT subject (user ID) is missing");
                filterChain.doFilter(request, response);
                return;
            }

            Long userId = Long.parseLong(subject);
            Optional<User> userOpt = userRepository.findById(userId);

            if (userOpt.isEmpty()) {
                logger.warn("User not found for ID from token: {}", userId);
                filterChain.doFilter(request, response);
                return;
            }

            User user = userOpt.get();
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(user.getEmail(), null, user.getAuthorities());

            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (JwtException e) {
            logger.warn("Invalid JWT token: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Error processing JWT authentication", e);
        }

        filterChain.doFilter(request, response);
    }

    private Claims parseToken(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(jwtKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
