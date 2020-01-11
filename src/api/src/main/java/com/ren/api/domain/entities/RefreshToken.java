package com.ren.api.domain.entities;

import java.util.Date;

import javax.persistence.*;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "refresh_tokens")
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