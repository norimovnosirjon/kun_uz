package com.mbs.api.util;

import com.mbs.api.dto.JwtDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 'Bilol Tuxtamurodov' on 02.04.2026
 * @project Lesson_132
 * @contact @BilolTuxtamurodov
 */
public class JwtUtil {
    private static final int tokenExpTime = 1000 * 3600 * 24 * 2; // 1 kun
    private static final String secretKey = "rifogjerjgueiRjufierbfiewreuwighuiqioreqhiug";

    public static String encode(String username, String role) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("username", username);
        extraClaims.put("role", role);
        return Jwts.builder()
                .claims(extraClaims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .issuer("Kunuz")
                .expiration(new Date(System.currentTimeMillis() + tokenExpTime))
                .signWith(getSecretKey()).compact();
    }
    public static JwtDTO decode(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        String username = (String) claims.get("username");
        String role = (String) claims.get("role");
        return new JwtDTO(username, role);
    }
    private static SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

}
