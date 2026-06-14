package com.huynhducphu.service.user.base;

import com.huynhducphu.model.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Admin 6/14/2026
 *
 **/
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class JwtBuilder {

    JwtEncoder jwtEncoder;

    public String buildJwt(User user, Long expirationRate) {

        Instant now = Instant.now();
        Instant expiredAt = now.plus(expirationRate, ChronoUnit.SECONDS);

        //  Header
        JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();

        // Body
        JwtClaimsSet claims = JwtClaimsSet
                .builder()
                .issuedAt(now)
                .expiresAt(expiredAt)
                .subject(user.getUsername())
                .build();

        return jwtEncoder
                .encode(JwtEncoderParameters.from(jwsHeader, claims))
                .getTokenValue();
    }


}
