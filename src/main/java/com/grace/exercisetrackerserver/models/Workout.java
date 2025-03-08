package com.grace.exercisetrackerserver.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grace.exercisetrackerserver.DTO.WorkoutDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "workout")
@Data
@ToString(exclude = "baseUser")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type; // e.g., Strength, Cardio, Yoga, etc.

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private int duration;

    @Column(name = "calories_burned", nullable = false)
    private int caloriesBurned;

    @ManyToOne
    @JoinColumn(name = "base_user_Id", nullable = false)
    @JsonBackReference // 🔹 Prevents infinite recursion
    private BaseUser baseUser; // 🔹 This links Workout to a User

    @JsonIgnore
    public WorkoutDTO getWorkoutDto(){
        WorkoutDTO workoutDTO = new WorkoutDTO();
        workoutDTO.setId(id);
        workoutDTO.setType(type);
        workoutDTO.setDate(date);
        workoutDTO.setDuration(duration);
        workoutDTO.setCaloriesBurned(caloriesBurned);

        if(baseUser != null){
            workoutDTO.setBaseUserId(baseUser.getId());
        }

        return workoutDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Workout workout = (Workout) o;
        return Objects.equals(id, workout.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


}
