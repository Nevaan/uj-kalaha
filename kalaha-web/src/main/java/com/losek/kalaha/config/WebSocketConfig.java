package com.losek.kalaha.config;

import com.losek.kalaha.controller.KalahaWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(kalahaWebSocketHandler(), "/ws-kalaha")
                .setAllowedOrigins("http://localhost:4200");
    }

    @Bean
    public KalahaWebSocketHandler kalahaWebSocketHandler() {
        return new KalahaWebSocketHandler();
    }
}
