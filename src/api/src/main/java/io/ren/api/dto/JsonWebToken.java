package io.ren.api.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JsonWebToken {

    private String accessToken;

    private String refreshToken;
}
