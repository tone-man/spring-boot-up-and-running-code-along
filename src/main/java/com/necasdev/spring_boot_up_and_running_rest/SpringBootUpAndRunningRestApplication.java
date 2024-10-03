package com.necasdev.spring_boot_up_and_running_rest;

import java.util.UUID;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;





@SpringBootApplication
public class SpringBootUpAndRunningRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootUpAndRunningRestApplication.class, args);
	}

}

/**
 *  RestApiDemoController
 */
@RestController
@RequestMapping("/coffees")
class RestApiCoffeeController {
    private List<Coffee> coffees = new ArrayList();

    RestApiCoffeeController() {
        coffees.addAll(List.of(
            new Coffee("Atomic"),
            new Coffee("Breaking Grounds"),
            new Coffee("Pourman's"),
            new Coffee("Les Tres Pontas")
        ));
    }

    
    //@RequestMapping(value = "/", method=RequestMethod.GET)
    @GetMapping
    Iterable<Coffee> getCoffees() {
        return coffees;
    }

    @GetMapping("/{id}")
    public Optional<Coffee> getCoffeeById(@PathVariable String id) {
        for(Coffee c: coffees) {
            if (c.getId().equals(id)) {
                return Optional.of(c);
            }
        }

        return Optional.empty();
    }
    
    @PostMapping
    Coffee postCoffee(@RequestBody Coffee coffee) {
        coffees.add(coffee);
        return coffee;
    }

    @PutMapping("/{id}")
    ResponseEntity<Coffee> putCoffee(@PathVariable String id, @RequestBody Coffee coffee) {
        int coffeeIndex = -1;
        
        for (Coffee curCoffee: coffees) {
            if(curCoffee.getId().equals(id)) {
                coffeeIndex = coffees.indexOf(curCoffee);
                coffees.set(coffeeIndex, coffee);
            }
        }

        return (coffeeIndex == -1) ? 
                new ResponseEntity<>(postCoffee(coffee), HttpStatus.CREATED) : 
                new ResponseEntity<>(coffee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    void deleteCoffee(@PathVariable String id) {
        coffees.removeIf(coffee -> coffee.getId().equals(id));
    }
    
}

/**
 *  Coffee Class
 */
class Coffee {
    private final String id;
    private String name;

    public Coffee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Coffee(String name) {
        this(UUID.randomUUID().toString(), name);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
