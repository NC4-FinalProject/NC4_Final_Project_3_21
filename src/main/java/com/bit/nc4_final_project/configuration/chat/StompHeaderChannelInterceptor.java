package com.bit.nc4_final_project.configuration.chat;

import com.bit.nc4_final_project.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE + 99) // 제일 먼저 실행하기 위해 우선순위를 높게 설정
public class StompHeaderChannelInterceptor implements ChannelInterceptor {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        log.info("===== WebSocket InterCeptor Arrived =====");
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        log.info("===== WebSocket InterCeptor : accessor :{}", accessor);
        StompCommand command = accessor.getCommand();
        log.info("===== WebSocket InterCeptor : command :{}", command);
        log.info("WebSocket Message : {}", message);
        int currentConnectedUserCount = 0;

        // websocket 연결시 헤더의 jwt token 유효성 검증
        if (command == StompCommand.CONNECT) {
            log.debug("===== WebSocket InterCepTer : CONNECT =====");
            // 현재 프로젝트 내의 jwtTokenProvider를 사용하여 토큰의 유효성을 검사
            String token = accessor.getFirstNativeHeader("Authorization");
            if (token == null || !token.startsWith("Bearer")) {
                log.info("===== WebSocket InterCeptor : Token is not valid. =====");
                throw new RuntimeException("Token is not valid.");
            } else if (jwtTokenProvider.validateAndGetUsername(token.substring(7)) == null) {
                log.info("===== WebSocket InterCeptor : The Token held by current user are unavailable. =====");
                throw new RuntimeException("The Token held by current user are unavailable.");
            }
        }
        // websocket sub 시 현재 채팅방의 접속 상태를 체크
        if (command == StompCommand.SUBSCRIBE) {
            log.debug("===== WebSocket InterCepTer : SUBSCRIBE =====");
            currentConnectedUserCount++;
        }
        if (command == StompCommand.DISCONNECT) {
            log.debug("===== WebSocket InterCepTer : DISCONNECT =====");
            currentConnectedUserCount--;
        }
        if (command == StompCommand.SEND) {
            if (currentConnectedUserCount < 2) {

            }
        }

        return message;
    }

//    @EventListener
//    public void handleWebSocketconnectedListener(SessionConnectedEvent event) {
//        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//        String sessionId = headerAccessor.getSessionId();
//
//    }
}
