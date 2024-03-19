package com.manavas.microservices.currencyexchangeservice.currency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CurrencyExchangeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Environment environment;

    private final CurrencyExchangeRepository currencyExchangeRepository;

    public CurrencyExchangeController(Environment environment, CurrencyExchangeRepository currencyExchangeRepository) {
        this.environment = environment;
        this.currencyExchangeRepository = currencyExchangeRepository;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to){
        //2024-03-14T19:01:47.462+01:00  INFO [currency-exchange,80d47dd66fd41d853cb28a5897723d7e,fbdc171a185a9e84] #SB3 2037 --- [currency-exchange] [nio-8000-exec-2] [80d47dd66fd41d853cb28a5897723d7e-fbdc171a185a9e84] c.m.m.c.c.CurrencyExchangeController     : retrieveExchangeValue called with USD to INR
        logger.info("retrieveExchangeValue called with {} to {}", from, to);
        //CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50));
        Optional<CurrencyExchange> currency = currencyExchangeRepository.findByFromAndTo(from, to);
        CurrencyExchange currencyExchange =
                currency.orElseThrow(() -> new RuntimeException("Not found miguel!!!"));
        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);

        return currencyExchange;
    }
}
