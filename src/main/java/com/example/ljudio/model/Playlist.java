package com.example.ljudio.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "playlist")
public class Playlist {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String playlist;

    @ManyToMany
    private List<Song> songs;

    //@ManyToOne
    //private User user;


}
