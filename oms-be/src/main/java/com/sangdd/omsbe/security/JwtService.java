package com.sangdd.omsbe.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;


@Service
public class JwtService {
    // Lưu ý: Secret Key phải dài ít nhất 32 ký tự để đảm bảo an toàn cho thuật toán HS256
    private static final String SECRET_KEY = "your-very-secure-and-long-secret-key-for-oms-project";

    // 1. Lấy Username từ Token (Hàm bạn đang thiếu)
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 2. Hàm dùng chung để lấy một thông tin (Claim) bất kỳ từ Token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // 3. Giải mã toàn bộ Token để lấy Claims
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 4. Tạo Key từ chuỗi Secret String
    private Key getSignInKey() {
        byte[] keyBytes = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // 5. Kiểm tra Token còn hạn hay không
    public boolean isTokenValid(String token) {
        try {
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // 6. Hàm tạo Token (Dùng khi Login thành công)
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 giờ
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
