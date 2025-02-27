package com.grace.exercisetrackerserver.services;

import com.grace.exercisetrackerserver.DTO.ActivityDTO;

public interface ActivityService {

    ActivityDTO postActivity(ActivityDTO dto);
}
