package com.grace.exercisetrackerserver.services;

import com.grace.exercisetrackerserver.DTO.ActivityDTO;

import java.util.List;

public interface ActivityService {

    ActivityDTO postActivity(ActivityDTO dto);

    List<ActivityDTO> getAllActivities();
}
