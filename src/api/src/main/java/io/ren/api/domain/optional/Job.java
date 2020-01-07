package io.ren.api.domain.optional;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "JOBS")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Temporal(value = TemporalType.DATE)
    public Date createdOn;

    public Long payment;

    @ManyToOne
    @JoinColumn(name = "location_id")
    public Location location;

    @ManyToOne
    @JoinColumn(name = "company_id")
    public Company company;
}
