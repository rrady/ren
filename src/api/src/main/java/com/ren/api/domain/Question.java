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
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String text;
    public String title;
    public Integer viewCount;

    @Temporal(value = TemporalType.DATE)
    public Date createdOn;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User creator;

    @ManyToMany
    @JoinTable(name = "questions_tags",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    public Set<Tag> tags;

    @OneToMany(mappedBy = "question")
    public Set<Answer> answers;

}
