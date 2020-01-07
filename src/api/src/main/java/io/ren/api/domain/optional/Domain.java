package io.ren.api.domain.optional;


import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "DOMAINS")
public class Domain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    @ManyToOne
    @JoinColumn(name = "company_id")
    public Company company;
}
