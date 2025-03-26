package org.example.handler;

import org.example.producer.Mandelbrot;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MandelbrotWebSocketHandler implements WebSocketHandler {

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        // Use Mandelbrot.generateData to pull a reactive stream of data
        return session.send(
                Mandelbrot.generateData()
                          .map(session::textMessage) // Convert data to WebSocket text messages
        )
        .then();

    }
}
