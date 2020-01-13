package com.ren.api.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    private String token;
    @Temporal(value = TemporalType.DATE)
    private Date createdAt;

    public RefreshToken(User user, String token) {
        this.user = user;
        this.token = token;
        this.createdAt = new Date();
    }
}