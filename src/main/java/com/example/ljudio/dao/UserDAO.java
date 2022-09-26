package com.example.ljudio.dao;

import com.example.ljudio.model.User;
import com.example.ljudio.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class UserDAO {

    private UserRepository userRepository;

    public Optional<User> findByID(long id) {
        return userRepository.findById(id);
    }


    public Optional<User> findByAccount(String username, String password) {
        return userRepository.findByAccount(username, password);
    }

    public User save(User newUser) {
        return userRepository.save(newUser);
    }
}
