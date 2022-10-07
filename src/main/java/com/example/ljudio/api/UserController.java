package com.example.ljudio.api;

import com.example.ljudio.model.User;
import com.example.ljudio.model.Playlist;
import com.example.ljudio.repository.CustomUserRepository;
import com.example.ljudio.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private final UserService userService;
    private CustomUserRepository userRepository;

    @GetMapping
    public List<User> getAllMembers() {
        return userRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getMemberById(@PathVariable("id") long id) {
        User member = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
        return ResponseEntity.ok(member);
    }

    @GetMapping("/{username}/{password}")
    public User getUserByAccount(@PathVariable String username, @PathVariable String password) {
        return userService.getUserByAccount(username, password);
    }

    @PostMapping
    public User addUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user) {
        User currentUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

        currentUser.setUsername(user.getUsername());
        currentUser.setEmail(user.getEmail());
        currentUser.setRoles(user.getRoles());


        User updatedUser = userRepository.save(currentUser);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
        User member = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

        userRepository.delete(member);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }



    @GetMapping("/{id}/playlists")
    public List<Playlist> getUserPlaylists(@PathVariable long id) {
        return userService.getUserPlaylists(id);
    }

}
