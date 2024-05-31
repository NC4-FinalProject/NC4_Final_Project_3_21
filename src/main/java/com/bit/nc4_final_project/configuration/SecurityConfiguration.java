package com.bit.nc4_final_project.configuration;


import com.bit.nc4_final_project.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(httpSecurityCorsConfigurer -> {

                })
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(httpSecurityHttpBasicConfigurer -> {
                    httpSecurityHttpBasicConfigurer.disable();
                })
                .sessionManagement(httpSecuritySessionManagementConfigurer -> {
//                    httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/review/**").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/user/sign-up/**").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/user/sign-in/**").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/user/sign-out/**").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/travel/**").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/bookmark/**").hasAnyRole("USER");
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/user/check-userid/**").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/user/check-username/**").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/community/**").authenticated();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/chat/**").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/chatting/**").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/upload/**").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/delete/**").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/update/**").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/oauth/kakao").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/oauth/google").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/user/areas/**").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/modifyuser/**").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/confirmPassword/**").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/updatePassword/**").permitAll();
                    authorizationManagerRequestMatcherRegistry.anyRequest().authenticated();
                })
                .addFilterAfter(jwtAuthenticationFilter, CorsFilter.class)
                .build();

    }
}


