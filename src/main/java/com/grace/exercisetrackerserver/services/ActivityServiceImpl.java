package com.grace.exercisetrackerserver.services;


import com.grace.exercisetrackerserver.DTO.ActivityDTO;
import com.grace.exercisetrackerserver.models.Activity;
import com.grace.exercisetrackerserver.repositories.ActivityRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;
    

    public ActivityDTO postActivity(ActivityDTO dto) { //this method will return activity dto

        //in the body of this method, we need to get the data from the dto
        // first we need create an object of this activity entity
        Activity activity = new Activity();

        activity.setDate(dto.getDate());
        activity.setSteps(dto.getSteps());
        activity.setDistance(dto.getDistance());
        activity.setCaloriesBurned(dto.getCaloriesBurned());

        return activityRepository.save(activity).getActivityDTO();     //why here double checking DTO

    }

    public List<ActivityDTO> getAllActivities() {
        List<Activity> activities = activityRepository.findAll();
        return activities.stream().map(Activity::getActivityDTO).collect(Collectors.toList());
    }

}
