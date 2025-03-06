package com.grace.exercisetrackerserver.controllers;

import com.grace.exercisetrackerserver.DTO.WorkoutDTO;
import com.grace.exercisetrackerserver.services.workout.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api")
@CrossOrigin("*")

public class WorkoutController {

    @Autowired
    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping("/workout")
    public ResponseEntity<?> postWorkout(@RequestBody WorkoutDTO workoutDTO){
        try{
            return ResponseEntity.ok(workoutService.postWorkout(workoutDTO));

        }catch(Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong.");
        }
    }
}
