package com.grace.exercisetrackerserver.services.workout;

import com.grace.exercisetrackerserver.DTO.WorkoutDTO;

public interface WorkoutService {

    WorkoutDTO postWorkout(WorkoutDTO workoutDTO);
}
