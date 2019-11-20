package io.ren.api.domain;

import java.util.Set;
import java.util.UUID;

import javax.persistence.*;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "creator")
    public Set<Question> questions;

    @OneToMany(mappedBy = "creator")
    public Set<Answer> answers;

    @OneToMany(mappedBy = "creator")
    public Set<Comment> comments;

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
