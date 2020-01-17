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
    private Long id;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private String title;

    private Integer viewCount;

    @Temporal(value = TemporalType.DATE)
    private Date createdOn;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "questions_tags",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private Set<Answer> answers;
}
