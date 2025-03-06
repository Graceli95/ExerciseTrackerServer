package com.grace.exercisetrackerserver.repositories;

import com.grace.exercisetrackerserver.models.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
}
