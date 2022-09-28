package com.example.ljudio.service;

import com.example.ljudio.dao.UserDAO;
import com.example.ljudio.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private UserDAO userDAO;

    public User getMemberById(long id) {
        return userDAO.findByID(id).orElse(null);
    }

    public User getUserByAccount(String username, String password) {
        return userDAO.findByAccount(username, password).orElse(null);
    }

    public User addMember(User newUser) {
        return userDAO.save(newUser);
    }

    public List<Playlist> getUserPlaylists(long id) {
        return getMemberById(id).getPlaylist();
    }
}
