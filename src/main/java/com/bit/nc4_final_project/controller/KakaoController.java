package com.bit.nc4_final_project.controller;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;



    @Controller
    public class KakaoController {

        @Value("${kakao.client_id}")
        private String kakaoClientId;

        @Value("${kakao.redirect_uri}")
        private String kakaoRedirectUri;

        @GetMapping("/oauth/kakao")
        public String kakaoLogin(@RequestParam String code) {
            RestTemplate rt = new RestTemplate();

            // 1. 인가 코드로 액세스 토큰 받기
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", "authorization_code");
            params.add("client_id", kakaoClientId);
            params.add("redirect_uri", kakaoRedirectUri);
            params.add("code", code);

            HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

            ResponseEntity<String> response = rt.exchange(
                    "https://kauth.kakao.com/oauth/token",
                    HttpMethod.POST,
                    kakaoTokenRequest,
                    String.class
            );

            // 2. 액세스 토큰으로 카카오 사용자 정보 받기
            // ...

            return "redirect:/"; // 로그인 성공 후 리다이렉트할 페이지
        }
    }
