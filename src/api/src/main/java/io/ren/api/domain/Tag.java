package io.ren.api.domain;

import lombok.*;

import javax.persistence.*;
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
@Table(name = "TAGS")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String text;

    @ManyToMany(mappedBy = "tags")
    public Set<Question> questions;

}
