package org.example.handler;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class MandelbrotWebSocketHandlerTest {

    @Test
    void testWebSocketHandlerSendsMandelbrotData() {
        WebSocketSession session = mock(WebSocketSession.class);

        // Capture WebSocket messages being sent by the handler
        when(session.send(any(Flux.class))).thenAnswer(invocation -> {
            // Return a Mono to simulate sending the data
            return Mono.empty();
        });

        // Create an instance of the handler
        MandelbrotWebSocketHandler handler = new MandelbrotWebSocketHandler();

        // Call the handle method and verify the behavior
        Mono<Void> result = handler.handle(session);

        // Verify that session.send() was called
        verify(session, times(1)).send(any(Flux.class));

        // Verify that the handler completes successfully
        StepVerifier.create(result)
                .expectComplete()
                .verify();
    }
}