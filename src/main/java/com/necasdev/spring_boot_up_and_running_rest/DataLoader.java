package com.necasdev.spring_boot_up_and_running_rest;

import java.util.List;

import com.necasdev.spring_boot_up_and_running_rest.Coffee;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {
    
    private final CoffeeRepository coffeeRepository;

    public DataLoader(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @PostConstruct
    private void loadData() {
        this.coffeeRepository.saveAll(List.of(
            new Coffee("Atomic"),
            new Coffee("Breaking Grounds"),
            new Coffee("Pourman's"),
            new Coffee("Les Tres Pontas")
        ));
    }
}
