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
@Table(name = "LOCATIONS")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String country;

    public String city;

    @OneToMany(mappedBy = "location")
    public Set<Job> jobs;

    @OneToMany(mappedBy = "location")
    public Set<Company> domains;

}
