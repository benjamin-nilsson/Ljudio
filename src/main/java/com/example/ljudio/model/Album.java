package com.example.ljudio.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "album")
public class Album {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long album_id;

    @Column
    private String spotify_id;

    @Column
    private String name;

    @Column
    private int songs;

    @Column
    private String albumImage;

    @ManyToOne
    private Artist artist;

    @Column
    @ManyToMany
    private List<Song> songs_list;

    public Album(String spotify_id, String name, int songs, String albumImage, Artist artist, List<Song> songs_list) {
        this.spotify_id = spotify_id;
        this.name = name;
        this.songs = songs;
        this.albumImage = albumImage;
        this.artist = artist;
        this.songs_list = songs_list;
    }
}
