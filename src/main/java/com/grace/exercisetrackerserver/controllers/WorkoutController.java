package com.grace.exercisetrackerserver.controllers;

import com.grace.exercisetrackerserver.DTO.WorkoutDTO;
import com.grace.exercisetrackerserver.models.Workout;
import com.grace.exercisetrackerserver.services.workout.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api")
@CrossOrigin("*")

public class WorkoutController {

    private final WorkoutService workoutService;

    @Autowired
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping("/newWorkout")
    public ResponseEntity<?> postWorkout(@RequestBody WorkoutDTO workoutDTO){
        try{
            return ResponseEntity.ok(workoutService.postWorkout(workoutDTO));

        }catch(Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(workoutDTO);
        }
    }

    @GetMapping("/users/{userId}/workouts")
    public ResponseEntity<List<Workout>> getUserWorkouts(@PathVariable Long userId){
       List<Workout> workouts = workoutService.getWorkoutsByUserId(userId);
       if(workouts.isEmpty()){
           return ResponseEntity.notFound().build();
       }else{
           return ResponseEntity.ok(workouts);
       }
    }


    @DeleteMapping("/workouts/{workoutId}")
    public ResponseEntity<Void> deleteWorkoutById(@PathVariable Long workoutId) {
        workoutService.deleteWorkoutById(workoutId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/workouts/{id}")
    public ResponseEntity<Workout> updateWorkout(@PathVariable Long id, @RequestBody Workout updatedWorkout) {
        Workout workout = workoutService.updateWorkout(id, updatedWorkout);
        if(workout == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(workout);
        }


    }





}
