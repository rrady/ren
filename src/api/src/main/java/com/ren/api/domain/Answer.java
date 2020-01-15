package com.ren.api.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
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
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String text;

    //    TODO: trebuie gasita o modalitate prin care sa nu dea acelasi utilizator de mai multe ori rating pentru answer
    public Integer rating;

    @Temporal(value = TemporalType.DATE)
    public Date createdOn;

    @Temporal(value = TemporalType.DATE)
    public Date editedOn;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User creator;

    @ManyToOne
    @JoinColumn(name = "question_id")
    public Question question;

    @OneToMany(mappedBy = "answer")
    public Set<Comment> comments;
}
