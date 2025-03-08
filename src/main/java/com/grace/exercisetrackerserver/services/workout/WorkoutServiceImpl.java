package com.grace.exercisetrackerserver.services.workout;

import com.grace.exercisetrackerserver.DTO.WorkoutDTO;
import com.grace.exercisetrackerserver.models.BaseUser;
import com.grace.exercisetrackerserver.models.Workout;
import com.grace.exercisetrackerserver.repositories.BaseUserRepository;
import com.grace.exercisetrackerserver.repositories.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final BaseUserRepository baseUserRepository;

    @Autowired
    public WorkoutServiceImpl(WorkoutRepository workoutRepository, BaseUserRepository baseUserRepository) {
        this.workoutRepository = workoutRepository;
        this.baseUserRepository = baseUserRepository;
    }


    public WorkoutDTO postWorkout(WorkoutDTO workoutDTO) {
        Workout workout = new Workout();

        workout.setDate(workoutDTO.getDate());
        workout.setType(workoutDTO.getType());
        workout.setDuration(workoutDTO.getDuration());
        workout.setCaloriesBurned(workoutDTO.getCaloriesBurned());

        // 🔹 Convert baseUserId (DTO) to BaseUser (Entity)
        BaseUser baseUser = baseUserRepository.findById(workoutDTO.getBaseUserId())
                .orElseThrow(() -> new RuntimeException("BaseUser with ID: " + workoutDTO.getBaseUserId() + "not found"));

        workout.setBaseUser(baseUser);  // 🔹 This links the Workout to the BaseUser

        Workout savedWorkout = workoutRepository.save(workout);

        return savedWorkout.getWorkoutDto();
    }

    // Ensure that each Workout has a valid BaseUser setup (should already be set by JPA)
    public List<Workout> getWorkoutsByUserId(Long userId){
        List<Workout> workouts = workoutRepository.findAllByBaseUser_Id(userId);  //📌 Ensures that only the workouts for the logged-in user are fetched.
        workouts.forEach(workout ->{
            if(workout.getBaseUser() == null){
                throw new RuntimeException("Workout with ID " + workout.getId() + " has no associated BaseUser!");
            }
        });
        return workouts; // Returning only the workouts for the given user
    }



//    @Transactional  //to keep the Hibernate session open while modifying related entities.
    public void deleteWorkoutById(Long id) {
        Workout workout = workoutRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Workout not found with ID: " + id));

        BaseUser baseUser = workout.getBaseUser(); //load the baseUser
        if(baseUser != null){
            baseUser.getWorkouts().remove(workout); // 🔹 Remove from user's list
        }
        workoutRepository.deleteById(id);
    }

    public Workout updateWorkout(Long id, Workout updatedWorkout) {
        return workoutRepository.findById(id)
                .map(workout ->{
                    workout.setDate(updatedWorkout.getDate());
                    workout.setType(updatedWorkout.getType());
                    workout.setDuration(updatedWorkout.getDuration());
                    workout.setCaloriesBurned(updatedWorkout.getCaloriesBurned());

                    if(updatedWorkout.getBaseUser() != null){
                        workout.setBaseUser(updatedWorkout.getBaseUser());
                    }
                    return workoutRepository.save(workout);
                })
                .orElseThrow(() -> new RuntimeException("Workout not found"));
    }






}
