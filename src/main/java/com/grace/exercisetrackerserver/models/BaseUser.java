package com.grace.exercisetrackerserver.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Objects;

@Entity
@Table(name = "base_user")
@Data

public class BaseUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;



    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BaseUser baseUser = (BaseUser) o;
        return Objects.equals(id, baseUser.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
