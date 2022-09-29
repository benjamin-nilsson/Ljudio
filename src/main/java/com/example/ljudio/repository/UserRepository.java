package com.example.ljudio.repository;

import com.example.ljudio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username LIKE %?1% AND u.password LIKE %?1%")
    Optional<User> findByAccount(String username, String password);
}
