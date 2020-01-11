package com.ren.api.domain.entities;

import javax.persistence.*;

import lombok.*;

@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "name")
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Tag(String name) {
        this.name = name;
    }
}