package com.bit.nc4_final_project.configuration.chat;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import com.bit.nc4_final_project.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE + 99) // 제일 먼저 실행하기 위해 우선순위를 높게 설정
public class StompHeaderChannelInterceptor implements ChannelInterceptor{
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        StompCommand command = accessor.getCommand();
        // websocket 연결시 헤더의 jwt token 유효성 검증
        if (command == StompCommand.CONNECT) {
            String token = accessor.getFirstNativeHeader("Authorization");
            // 현재 프로젝트 내의 jwtTokenProvider를 사용하여 토큰의 유효성을 검사
            if (jwtTokenProvider.validateAndGetUsername(token) == null) {
                throw new AccessDeniedException("Token is not valid.");
            }
        }
        log.info("preSend: " + accessor.toString());
        return message;
    }
}
