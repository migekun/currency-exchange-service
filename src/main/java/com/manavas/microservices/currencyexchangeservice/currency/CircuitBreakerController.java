package com.manavas.microservices.currencyexchangeservice.currency;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @GetMapping("/sample-api")
    //@Retry(name="sample-api", fallbackMethod = "hardcodedResponse")
    //@CircuitBreaker(name="default", fallbackMethod = "hardcodedResponse")
    //@RateLimiter(name = "default")
    //10sec allow 10000 calls to API
    //@Bulkhead(name = "default")
    @Bulkhead(name = "sample-api")
    public String sampleAPI() {
        logger.info("Sample API call received");
        //ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/dummyUrlThatWillFail", String.class);
        //return forEntity.getBody();
        return "sample-api";
    }

    public String hardcodedResponse(Exception ex) {
        logger.info("hardcodedResponse");
        return "fallback-response";
    }
}
