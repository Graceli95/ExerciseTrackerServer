package com.grace.exercisetrackerserver.repositories;

import com.grace.exercisetrackerserver.models.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseUserRepository extends JpaRepository<BaseUser, Long> {
    Optional<BaseUser> findByUsername(String username);
    Optional<BaseUser> findByEmail(String email);

}
