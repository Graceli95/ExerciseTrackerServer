package com.grace.exercisetrackerserver.models;

import com.grace.exercisetrackerserver.DTO.ActivityDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


import java.util.Date;

@Entity
@Data
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;
    private int steps;
    private double distance;
    private int caloriesBurned;


    public ActivityDTO getActivityDTO(){

        ActivityDTO activityDTO = new ActivityDTO();

        activityDTO.setId(id);
        activityDTO.setDate(date);
        activityDTO.setSteps(steps);
        activityDTO.setDistance(distance);
        activityDTO.setCaloriesBurned(caloriesBurned);

        return activityDTO;

    }
}
/*
questions to ask:
why we need DTO here?
what situation doesn't need DTO?
 */