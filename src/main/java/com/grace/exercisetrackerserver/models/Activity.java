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


        activityDTO.setDate(date);
        activityDTO.setSteps(steps);
        activityDTO.setDistance(distance);
        activityDTO.setCaloriesBurned(caloriesBurned);

        return activityDTO;
    }
}

/**
 * This code is used to **convert entity objects (`Activity`) into their corresponding DTOs (`ActivityDTO`)**. The transformation is necessary because:
 * - **Separation of Concerns**: The `Activity` entity is tied to the database structure, while `ActivityDTO` is designed for transferring data (e.g., to clients via APIs) without exposing internal data structures.
 * - **Decoupling**: Using a DTO allows more flexibility in structuring the data sent to the client. For example, you can include or exclude fields, rename fields, modify data formats, and so on without altering the database schema.
 *
 * In this context, the `getActivityDTO()` method in `Activity` simplifies this transformation.
 */
