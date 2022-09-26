package com.example.ljudio.model;

import com.example.ljudio.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "Role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte role_id;

    @Column
    @Enumerated(EnumType.STRING)
    private Role roles;
}
