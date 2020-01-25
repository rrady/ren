package com.ren.api.domain;

import java.util.Date;

import javax.persistence.*;

import lombok.*;

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

    @Column(nullable = false)
    private String token;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(columnDefinition="DATETIME")
    private Date createdAt;

    public RefreshToken(User user, String token) {
        this.user = user;
        this.token = token;
        this.createdAt = new Date();
    }
}