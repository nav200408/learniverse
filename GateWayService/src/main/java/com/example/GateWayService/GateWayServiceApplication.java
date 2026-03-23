package com.example.GateWayService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableDiscoveryClient
public class GateWayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GateWayServiceApplication.class, args);
	}

	@Bean
	public org.springframework.cloud.gateway.filter.ratelimit.KeyResolver userKeyResolver() {
		return exchange -> {
			if (exchange.getRequest().getRemoteAddress() != null) {
				return Mono.just(exchange.getRequest()
						.getRemoteAddress()
						.getAddress()
						.getHostAddress());
			}
			return Mono.just("anonymous");
		};
	}

	@Bean
	public org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter redisRateLimiter() {
		return new org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter(10, 20, 1);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder,
										   org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter rateLimiter,
										   org.springframework.cloud.gateway.filter.ratelimit.KeyResolver keyResolver) {

		return builder.routes()

				.route("user-route", r -> r.path("/user-route/**")
						.filters(f -> f.stripPrefix(1)
								.requestRateLimiter(c -> c
										.setRateLimiter(rateLimiter)
										.setKeyResolver(keyResolver)
								)
								.circuitBreaker(c -> c
										.setName("CircuitBreaker")
										.setFallbackUri("forward:/fallback")
								)
						)
						.uri("lb://UserService"))

				.route("course-route", r -> r.path("/course-route/**")
						.filters(f -> f.stripPrefix(1)
								.requestRateLimiter(c -> c
										.setRateLimiter(rateLimiter)
										.setKeyResolver(keyResolver)
								)
								.circuitBreaker(c -> c
										.setName("CircuitBreaker")
										.setFallbackUri("forward:/fallback")
								)
						)
						.uri("lb://CourseService"))

				.route("email-route", r -> r.path("/email-route/**")
						.filters(f -> f.stripPrefix(1)
								.requestRateLimiter(c -> c
										.setRateLimiter(rateLimiter)
										.setKeyResolver(keyResolver)
								)
								.circuitBreaker(c -> c
										.setName("CircuitBreaker")
										.setFallbackUri("forward:/fallback")
								)
						)
						.uri("lb://EmailService"))

				.route("enrollment-route", r -> r.path("/enrollment-route/**")
						.filters(f -> f.stripPrefix(1)
								.requestRateLimiter(c -> c
										.setRateLimiter(rateLimiter)
										.setKeyResolver(keyResolver)
								)
								.circuitBreaker(c -> c
										.setName("CircuitBreaker")
										.setFallbackUri("forward:/fallback")
								)
						)
						.uri("lb://EnrollmentService"))

				.route("payment-route", r -> r.path("/payment-route/**")
						.filters(f -> f.stripPrefix(1)
								.requestRateLimiter(c -> c
										.setRateLimiter(rateLimiter)
										.setKeyResolver(keyResolver)
								)
								.circuitBreaker(c -> c
										.setName("CircuitBreaker")
										.setFallbackUri("forward:/fallback")
								)
						)
						.uri("lb://PaymentService"))

				.route("stream-route", r -> r.path("/stream-route/**")
						.filters(f -> f.stripPrefix(1)
								.requestRateLimiter(c -> c
										.setRateLimiter(rateLimiter)
										.setKeyResolver(keyResolver)
								)
								.circuitBreaker(c -> c
										.setName("CircuitBreaker")
										.setFallbackUri("forward:/fallback")
								)
						)
						.uri("lb://StreamingService"))

				.route("wishlist-route", r -> r.path("/wishlist-route/**")
						.filters(f -> f.stripPrefix(1)
								.requestRateLimiter(c -> c
										.setRateLimiter(rateLimiter)
										.setKeyResolver(keyResolver)
								)
								.circuitBreaker(c -> c
										.setName("CircuitBreaker")
										.setFallbackUri("forward:/fallback")
								)
						)
						.uri("lb://WishlistService"))

				.route("category-route", r -> r.path("/category-route/**")
						.filters(f -> f.stripPrefix(1)
								.requestRateLimiter(c -> c
										.setRateLimiter(rateLimiter)
										.setKeyResolver(keyResolver)
								)
								.circuitBreaker(c -> c
										.setName("CircuitBreaker")
										.setFallbackUri("forward:/fallback")
								)
						)
						.uri("lb://CategoryService"))

				.build();
	}

	@Bean
	public WebFilter corsFilter() {
		return (ServerWebExchange ctx, WebFilterChain chain) -> {
			ServerHttpRequest request = ctx.getRequest();
			if (CorsUtils.isCorsRequest(request)) {
				ServerHttpResponse response = ctx.getResponse();
				HttpHeaders headers = response.getHeaders();
				headers.add("Access-Control-Allow-Origin", "http://localhost:5173");
				headers.add("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS");
				headers.add("Access-Control-Max-Age", "10000");
				headers.add("Access-Control-Allow-Headers", "*");
				headers.add("Access-Control-Expose-Headers", "Authorization, Content-Type");

				if (request.getMethod() == HttpMethod.OPTIONS) {
					response.setStatusCode(HttpStatus.OK);
					return Mono.empty();
				}
			}
			return chain.filter(ctx);
		};
	}
}
