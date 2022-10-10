package com.example.ljudio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "song")
public class Song {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long song_id;

    @Column
    private String spotify_id;

    @Column
    private String title;

    @Column
    private String albumId;

    @Column
    private String releaseDate;

    @Column
    @ManyToMany
    private List<Artist> artist_list;

    public Song(String spotify_id, String title, String albumId, String releaseDate, List<Artist> artist_list) {
        this.spotify_id = spotify_id;
        this.title = title;
        this.albumId = albumId;
        this.releaseDate = releaseDate;
        this.artist_list = artist_list;
    }
}
