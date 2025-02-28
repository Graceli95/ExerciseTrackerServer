package com.grace.exercisetrackerserver.controllers;

import com.grace.exercisetrackerserver.DTO.ActivityDTO;
import com.grace.exercisetrackerserver.services.ActivityService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController       // It is used to create RESTful web services by combining `@Controller` and `@ResponseBody`.
@RequestMapping("/api")
@RequiredArgsConstructor //Ensures the constructor is generated for final fields
@CrossOrigin("*")
public class ActivityController {

    //inject ActivityService
    private final ActivityService activityService;


    @PostMapping("/activity")
    public ResponseEntity<?> postActivity(@RequestBody ActivityDTO dto){   //Business logic for saving the activity


        ActivityDTO createActivity = activityService.postActivity(dto); //create an object of ActivityDTO

        if(createActivity != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createActivity);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong.");
        }
    }



    @GetMapping("/activities")
    public ResponseEntity<?> getAllActivities() { // Business logic for fetching all activities
        try{
            return ResponseEntity.ok(activityService.getAllActivities());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong.");
        }
    }
}

//Controller exposes REST APIs for managing activities.

/**
 * ### What Is `@RestController`?
 * - `@RestController` is a Spring annotation that marks a class as a controller where every method returns a domain object (e.g., JSON, XML) instead of a view (like JSP, Thymeleaf, etc.).
 * - It eliminates the need to add `@ResponseBody` to every method. This means that the return values from the controller methods are automatically serialized into the desired format (e.g., JSON) and sent as the HTTP response body.
 *
 * The `@RestController` annotation implicitly includes:
 * 1. `@Controller`: Marks the class as a web controller (handles HTTP requests).
 * 2. `@ResponseBody`: Indicates that the return value of the controller's methods will be serialized into an HTTP response body automatically.
 *
 *
 * Here’s how `@RestController` affects the behavior of the `ActivityController` class:
 * 1. **Mark the Class as a Spring Controller**
 *     - Spring detects the `ActivityController` class as a component responsible for handling HTTP requests and associates it with the specified base URL (`/api`).
 *
 * 2. **Implicit `@ResponseBody` Behavior**
 *     - The `ResponseEntity<?>` objects returned by the methods are converted into JSON and sent as the body of the HTTP response. For example:
 *         - If the `postActivity` method is called with appropriate input, the response body will include the created `ActivityDTO` in JSON format.
 *         - If there is an error, a string message (JSON-formatted by Spring) indicating the error will be sent as the response body. For instance:
 */