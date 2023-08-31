package com.github.candy.store.config.security;

import com.github.candy.store.modules.token.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.RequestContextFilter;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends RequestContextFilter {

    private final static String TOKEN_PREFIX = "Bearer ";

    private final TokenRepository tokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (Objects.nonNull(authHeader) && authHeader.startsWith(TOKEN_PREFIX)) {
            final String jwt = authHeader.substring(7);
            final String userEmail = jwtTokenProvider.extractUsername(jwt);
            if (Objects.nonNull(userEmail) && hasAuthentication()) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
                var isTokenValid = tokenRepository.findByToken(jwt)
                        .map(t -> {
                            request.setAttribute("token", t);
                            return !t.isRevoked();
                        })
                        .orElse(false);
                if (isTokenValid) {
                    if (jwtTokenProvider.isTokenExpired(jwt)) {
                        tokenRepository.revokeToken(jwt);
                    } else {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                        org.springframework.security.web.authentication.WebAuthenticationDetails webAuthenticationDetails = new WebAuthenticationDetailsSource().buildDetails(request);
                        authToken.setDetails(webAuthenticationDetails);
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private static boolean hasAuthentication() {
        return Objects.isNull(SecurityContextHolder.getContext().getAuthentication());
    }
}
