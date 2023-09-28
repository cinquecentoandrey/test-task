package com.cinquecento.gateway.filter;


import com.cinquecento.apigatewaystarter.exception.InvalidJWTException;
import com.cinquecento.apigatewaystarter.exception.MissingAuthorizationHeaderException;
import com.cinquecento.apigatewaystarter.exception.UserNotApprovedException;
import com.cinquecento.jwtstarter.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final JWTUtil jwtUtil;
    @Value("${error-code.auth}")
    private Integer AUTH_ERROR;
    @Value("${error-code.invalid-token}")
    private Integer INVALID_TOKEN;

    @Autowired
    public AuthenticationFilter(JWTUtil jwtUtil) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new MissingAuthorizationHeaderException(AUTH_ERROR, "The header not found.");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    String jwt = authHeader.substring(7);

                    try {
                        Map<String, String> claims = jwtUtil.validateTokenAndRetrieveClaims(jwt);
                        String isApproved = claims.get("isApproved");

                        if(isApproved.equals("false")) {
                            throw new UserNotApprovedException(AUTH_ERROR, "Your profile is not verified.");
                        }

                    } catch (Exception e) {
                        throw new InvalidJWTException(INVALID_TOKEN, "Invalid token");
                    }
                }
            return chain.filter(exchange);
        }));
    }

    public static class Config {

    }
}
