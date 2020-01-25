package com.ren.api.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import lombok.*;

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
    private Long id;

    @Column(nullable = false)
    private String text;

    private Integer rating;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(columnDefinition="DATETIME", nullable = false)
    private Date createdOn;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(columnDefinition="DATETIME", nullable = true)
    private Date editedOn;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @OneToMany(mappedBy = "answer")
    private Set<Comment> comments;
}
