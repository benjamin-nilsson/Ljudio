package com.example.ljudio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.time.LocalTime;

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
    private String album;

    @Column
    private String songLength;

    @Column
    private LocalTime releaseDate;

    @Column
    @ManyToMany
    @JsonIgnore
    private List<Artist> artist_list;
}
