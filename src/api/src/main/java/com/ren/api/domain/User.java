package com.ren.api.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by aneagu on 03/01/2020.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "user")
    public Set<Question> questions;

    @OneToMany(mappedBy = "user")
    public Set<Answer> answers;

    @OneToMany(mappedBy = "creator")
    public Set<Comment> comments;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "image")
    private String image;

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
