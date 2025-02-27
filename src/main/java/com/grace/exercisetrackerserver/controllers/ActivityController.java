package com.grace.exercisetrackerserver.controllers;

import com.grace.exercisetrackerserver.DTO.ActivityDTO;
import com.grace.exercisetrackerserver.services.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
//@RequiredArgsConstructor //Ensures the constructor is generated for final fields
@CrossOrigin("*")  //for the path, we pass it as static
public class ActivityController {

    //inject ActivityService
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    //this method will return response entity and we will import it from spring.framework.http
    @PostMapping("/activity")
    public ResponseEntity<?> postActivity(@RequestBody ActivityDTO dto){

        ActivityDTO createActivity = activityService.postActivity(dto); //create an object of ActivityDTO

        if(createActivity != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createActivity);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong.");
        }
    }
}
