package java.com.bit.nc4_final_project.jwt;


import com.bit.nc4_final_project.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

// JWT TOKEN을 발행하고 받아온 JWT TOKEN이 유효한지 검사해주는 클래스
@Component
public class JwtTokenProvider {
    // JWT TOKEN의 signature 부분이 될 서명 키 선언
    // bitcampdevops4finalproject20240419endoftheclass 을 BASE64 인코딩한 값
    private static final String SECRET_KEY = "Yml0Y2FtcGRldm9wczRmaW5hbHByb2plY3QyMDI0MDQxOWVuZG9mdGhlY2xhc3M=";

    // SECRET_KEY를 KEY 객체로 변환
    SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    // 사용자 정보를 받아서 JWT TOKEN을 생성하는 메소드
    public String create(User user) {
        // 토큰 만료일 생성
        // 생성된 날짜로부터 하루 뒤를 만료일자로 설정
        Date expireDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));

        // JWT Token 생성하여 리턴
        // Jwts 객체를 사용하기 때문에 JWT TOKEN의 헤더에 typ에 jwt로 입력된다.
        return Jwts.builder()
                // 시그니처 부분과 헤더의 알고리즘 지정
                .signWith(key, SignatureAlgorithm.HS256)
                // payload(데이터) 부분
                // sub(subject: 토큰의 주인)
                .subject(user.getUserId())
                // iss(issuer: 토큰의 발행주체)
                .issuer("final project")
                // isa(issuedAt: 토큰의 발행일자)
                .issuedAt(new Date())
                // 토큰 만료일자
                .expiration(expireDate)
                .compact();
    }

    // 받아온 JWT TOKEN이 유효한지를 검사하는 메소드
    // 유효하면 Subject에 담겨있는 username을 리턴한다.
    public String validateAndGetUsername(String token) {
        /*
         * 받아온 토큰 값을 파싱해서 유효성을 검사
         * 토큰에 있는 시그니쳐와 서버에서 가지고있는 시그니쳐 값과 비교
         * 일치하면 Claims 객체를 리턴
         * */
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }
}
