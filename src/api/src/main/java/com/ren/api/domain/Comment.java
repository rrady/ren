package com.ren.api.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false)
    private Date createdOn;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User creator;
}
