package com.ren.api.domain.entities;

import java.util.Date;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Question question;
    private String body;
    @Temporal(value = TemporalType.DATE)
    private Date createdAt;

    public Comment(String body, User user, Question question) {
        this.body = body;
        this.user = user;
        this.question = question;
        this.createdAt = new Date();
    }
}