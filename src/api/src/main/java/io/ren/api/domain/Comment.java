package io.ren.api.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "COMMENTS")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String text;

    @Temporal(value = TemporalType.DATE)
    public Date createdOn;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    public Answer answer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User creator;
}
