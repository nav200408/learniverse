package com.example.GateWayService;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class SomeFilter extends AbstractGatewayFilterFactory {
    @Override
    public GatewayFilter apply(Object config) {
        return ((exchange, chain) -> {
            System.out.println("SomeFilter");
            if(exchange.getRequest().getQueryParams().getFirst("myParam").equals("1")){
                exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
                exchange.getResponse().getHeaders().add("Content-Type", "text/plain");
                byte[] bytes = "Invalid request".getBytes(StandardCharsets.UTF_8);
                return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(bytes)));
            }
            return chain.filter(exchange);
        });
    }


}
