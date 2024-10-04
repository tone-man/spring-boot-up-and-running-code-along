package com.necasdev.spring_boot_up_and_running_rest;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface
 */
public interface CoffeeRepository extends CrudRepository<Coffee, String> {
    
}