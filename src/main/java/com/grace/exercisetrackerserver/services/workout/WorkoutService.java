package com.grace.exercisetrackerserver.services.workout;

import com.grace.exercisetrackerserver.DTO.WorkoutDTO;
import com.grace.exercisetrackerserver.models.Workout;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WorkoutService {

    WorkoutDTO postWorkout(WorkoutDTO workoutDTO);

    List<Workout> getWorkoutsByUserId(Long userId);


    void deleteWorkoutById(Long id);

    Workout updateWorkout(Long id, Workout updatedWorkout);


}
