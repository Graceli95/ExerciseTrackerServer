package com.grace.exercisetrackerserver.repositories;

import com.grace.exercisetrackerserver.models.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
   List<Workout> findAllByBaseUser_Id(Long userId); // 📌 This allows us to find all workouts for a specific user ID

}
