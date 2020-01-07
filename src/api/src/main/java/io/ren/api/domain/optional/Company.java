package io.ren.api.domain.optional;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "COMPANIES")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @OneToMany(mappedBy = "company")
    public Set<Job> jobs;

    @OneToMany(mappedBy = "company")
    public Set<Domain> domains;

    @ManyToOne
    public Location location;

}
