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
				.route("receive-route", r -> r.path("/user/**")
						.filters(f -> f.stripPrefix(1).filter(someFilter.apply(new SomeFilter())).filter(otherFilter.apply(new OtherFilter()))
								.circuitBreaker(c -> c.setName("CircuitBreaker")
										.getFallbackUri()))
						.uri("lb://receiveData"))

				.build();
	}
}

