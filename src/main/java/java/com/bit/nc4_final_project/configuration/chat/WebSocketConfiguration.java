package java.com.bit.nc4_final_project.configuration.chat;

import com.bit.nc4_final_project.configuration.chat.StompHeaderChannelInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Slf4j
@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
    private final StompHeaderChannelInterceptor stompHeaderChannelInterceptor;

    // WebSocket 엔드포인트 등록
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chatting")
                .setAllowedOrigins("http://localhost:3000")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/sub");            // /topic이 붙은 주소로 메시지를 보내면 해당 주소를 구독하고 있는 클라이언트에게 메시지를 전달
        registry.setApplicationDestinationPrefixes("/pub");        // /app이 붙은 메세지들은 @MessageMapping이 붙은 메소드로 라우팅
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(stompHeaderChannelInterceptor);
    }
}
