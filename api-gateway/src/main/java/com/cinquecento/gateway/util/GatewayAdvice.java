package com.cinquecento.gateway.util;

import com.cinquecento.apigatewaystarter.exception.BaseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.SneakyThrows;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@ControllerAdvice
@Order(-1)
public class GatewayAdvice implements ErrorWebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {

        if (ex instanceof BaseException) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(makeResponse((BaseException) ex).getBytes());

            return exchange.getResponse().writeWith(Mono.just(buffer));
        }

        return Mono.error(ex);
    }

    @SneakyThrows
    private String makeResponse(BaseException exception) {

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonNode = mapper.createObjectNode();

        jsonNode.put("errorCode", exception.getErrorCode());
        jsonNode.put("message", exception.getMessage());
        jsonNode.put("timestamp", LocalDateTime.now().toString());

        return mapper.writeValueAsString(jsonNode);
    }
}
