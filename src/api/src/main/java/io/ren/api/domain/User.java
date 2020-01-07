package io.ren.api.domain;

import java.util.Set;

import javax.persistence.*;

import io.ren.api.domain.optional.Article;
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

    @OneToMany(mappedBy = "creator")
    public Set<Article> articles;

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}
