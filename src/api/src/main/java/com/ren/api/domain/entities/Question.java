package com.ren.api.domain.entities;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    private String title;
    private String description;
    private String body;
    @OneToMany
    private List<Tag> tags;
    @Temporal(value = TemporalType.DATE)
    private Date createdAt;
    @Temporal(value = TemporalType.DATE)
    private Date updatedAt;

    public Question(String title, String description, String body, String[] tagList, User user) {
        this(title, description, body, tagList, user, new Date());
    }

    public Question(String title, String description, String body, String[] tagList, User user, Date createdAt) {
        this.title = title;
        this.description = description;
        this.body = body;
        this.tags = Arrays.stream(tagList).collect(toSet()).stream().map(Tag::new).collect(toList());
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }

    public void update(String title, String description, String body) {
        if (!"".equals(title)) {
            this.title = title;
        }
        if (!"".equals(description)) {
            this.description = description;
        }
        if (!"".equals(body)) {
            this.body = body;
        }
        this.updatedAt = new Date();
    }
}