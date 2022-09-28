package com.example.ljudio.api;

import com.example.ljudio.dao.UserDAO;
import com.example.ljudio.model.Playlist;
import com.example.ljudio.model.User;
import com.example.ljudio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping("/id")
    public User getMemberById(long id) {
        return userService.getMemberById(id);
    }

    @GetMapping("/{username}/{password}")
    public User getUserByAccount(@PathVariable String username, @PathVariable String password) {
        return userService.getUserByAccount(username, password);
    }

    @PostMapping
    public User addUser(@RequestBody User newUser) {
        return userService.addMember(newUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user) {
        User currentUser = userService.getMemberById(id);
        currentUser.setUsername(user.getUsername());
        currentUser.setName(user.getName());
        currentUser.setBirthdate(user.getBirthdate());
        currentUser.setCountry(user.getCountry());
        currentUser.setEmail(user.getEmail());

        // userService.addMember(user);

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/{id}/playlists")
    public List<Playlist> getUserPlaylists(@PathVariable long id) {
        return userService.getUserPlaylists(id);
    }

}
