package io.ren.api.domain;

import java.util.Date;

import javax.persistence.*;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "REFRESHTOKEN")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Temporal(value = TemporalType.DATE)
    private Date createdAt;

    public RefreshToken(User user, String token) {
        this.user = user;
        this.token = token;
        this.createdAt = new Date(System.currentTimeMillis());
    }

    public User getUser() {
        return this.user;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public String getToken() {
        return this.token;
    }
}
