package com.example.GateWayService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GateWayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GateWayServiceApplication.class, args);
	}
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder,SomeFilter someFilter,OtherFilter otherFilter) {
		return builder.routes()
				.route("user-route", r -> r.path("/userRoute/**")
						.filters(f -> f.stripPrefix(1)
								.circuitBreaker(c -> c.setName("CircuitBreaker")
										.getFallbackUri()))
						.uri("lb://UserService"))
				.route("course-route", r->r.path("/courseRoute/**")
				.filters(f -> f.stripPrefix(1)
						.circuitBreaker(c -> c.setName("CircuitBreaker")
								.getFallbackUri()))
				.uri("lb://CourseService"))
				.route("email-route", r->r.path("/emailRoute/**")
						.filters(f -> f.stripPrefix(1)
								.circuitBreaker(c -> c.setName("CircuitBreaker")
										.getFallbackUri()))
						.uri("lb://EmailService"))
				.route("enrollment-route", r->r.path("/enrollmentRoute/**")
						.filters(f -> f.stripPrefix(1)
								.circuitBreaker(c -> c.setName("CircuitBreaker")
										.getFallbackUri()))
						.uri("lb://EnrollmentService"))
				.route("payment-route", r->r.path("/paymentRoute/**")
						.filters(f -> f.stripPrefix(1)
								.circuitBreaker(c -> c.setName("CircuitBreaker")
										.getFallbackUri()))
						.uri("lb://PaymentService"))
				.route("stream-route", r->r.path("/streamRoute/**")
						.filters(f -> f.stripPrefix(1)
								.circuitBreaker(c -> c.setName("CircuitBreaker")
										.getFallbackUri()))
						.uri("lb://StreamingService"))
				.route("wishlist-route", r->r.path("/wishlistRoute/**")
						.filters(f -> f.stripPrefix(1)
								.circuitBreaker(c -> c.setName("CircuitBreaker")
										.getFallbackUri()))
						.uri("lb://WishlistService"))
				.build();
	}
}

