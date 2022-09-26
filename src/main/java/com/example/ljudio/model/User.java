package com.example.ljudio.model;

import com.example.ljudio.service.UserService;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;

    @Column
    private String username;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private LocalDate birthdate;

    @Column
    private Role role;

    @Column
    ManyToOne
    private Country country;

    @Column
    OneToMany
    private List<UserService> playlist;
}
