package org.example.config;

import org.example.handler.MandelbrotWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;

import java.util.Map;

@Configuration
public class WebSocketConfig {

    @Bean
    public HandlerMapping webSocketHandlerMapping(MandelbrotWebSocketHandler mandelbrotWebSocketHandler) {
        // Map the WebSocket endpoint "/ws/mandelbrot" to the handler
        return new SimpleUrlHandlerMapping(Map.of("/ws/mandelbrot", mandelbrotWebSocketHandler), 10);
    }
}