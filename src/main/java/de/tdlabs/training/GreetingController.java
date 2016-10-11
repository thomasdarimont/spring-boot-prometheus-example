package de.tdlabs.training;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
class GreetingController {

    @GetMapping("/greet")
    String greet(@RequestParam(defaultValue = "World") String name) {
        return "Hello: " + name + " " + LocalDateTime.now();
    }
}
