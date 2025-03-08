package com.grace.exercisetrackerserver.services;

import com.grace.exercisetrackerserver.models.BaseUser;
import com.grace.exercisetrackerserver.repositories.BaseUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BaseUserService {

    private final BaseUserRepository baseUserRepository;

    @Autowired
    public BaseUserService(BaseUserRepository baseUserRepository) {
        this.baseUserRepository = baseUserRepository;
    }


        public Optional<BaseUser> getUserById(Long id) {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException("ID must be a positive number.");
            }
            return baseUserRepository.findById(id);
        }



    public BaseUser create(BaseUser baseUser) {
        return baseUserRepository.save(baseUser);
    }

    public void deleteUserById(Long id) {
        if (id == null || id <= 0) {
                throw new IllegalArgumentException("ID must be a positive number.");
        }
        baseUserRepository.deleteById(id);
        System.out.println("Deleted user with ID: " + id); // Replace with proper logging
        }

        public List<BaseUser> getAllUsers() {
            return baseUserRepository.findAll();
        }
}


