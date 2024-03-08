package com.cci.demohello.config.security.filter;

import com.cci.demohello.persistence.entity.User;
import com.cci.demohello.service.auth.JWTService;
import com.cci.demohello.service.auth.UserSecurityService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserSecurityService userService;
    private final JWTService jwtService;

    public JwtAuthenticationFilter(UserSecurityService userService, JWTService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        if (!StringUtils.hasText(authorizationHeader) ||
                !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = getJwt(authorizationHeader);
        String username = jwtService.extractUsername(jwt);

        User user = userService.findByUsername(username);

        UsernamePasswordAuthenticationToken authUsernamePasswordToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), null, user.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authUsernamePasswordToken);
        filterChain.doFilter(request, response);
    }

    private String getJwt(String authorization) {
        return authorization.split(" ")[1];
    }
}
