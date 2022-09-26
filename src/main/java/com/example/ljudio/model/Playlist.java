package com.example.ljudio.model;

import javax.persistence.*;

@Entity(name="playlist")
public class Playlist {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;
    private String playlist;

    //@ManyToMany
    //private Song song;

    //@ManyToOne
    //private User user;

    public Playlist(){
    }

    public Playlist(Integer id, String playlist) {
        this.id = id;
        this.playlist = playlist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaylist() {
        return playlist;
    }

    public void setPlaylist(String playlist) {
        this.playlist = playlist;
    }

    /*public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    */
}
