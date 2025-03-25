package org.example.producer;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MandelbrotSetComputationTest {

    @Test
    void testComputeMandelbrotInsideSet() {
        int x = 400;
        int y = 400;
        String result = Mandelbrot.computeMandelbrot(x, y);
        String[] parts = result.split(",");
        int iterations = Integer.parseInt(parts[2]);
        assertEquals(1000, iterations);
    }

    @Test
    void testComputeMandelbrotOutsideSet() {
        int x = 0;
        int y = 0;
        String result = Mandelbrot.computeMandelbrot(x, y);
        String[] parts = result.split(",");
        int iterations = Integer.parseInt(parts[2]);
        assertTrue(iterations < 1000);
    }

    @Test
    void testGenerateDataStream() {
        Flux<String> dataStream = Mandelbrot.generateData();
        StepVerifier.create(dataStream)
                .expectNextMatches(data -> {
                    String[] parts = data.split(",");
                    return parts.length == 3
                            && Integer.parseInt(parts[0]) >= 0
                            && Integer.parseInt(parts[1]) >= 0
                            && Integer.parseInt(parts[2]) >= 0;
                })
                .thenCancel()
                .verify();
    }

    @Test
    @Disabled("This test takes a very long time to run")
    void testGenerateDataCompleteness() {
        Flux<String> dataStream = Mandelbrot.generateData();
        StepVerifier.create(dataStream)
                .expectNextCount(800 * 800)
                .expectComplete()
                .verify();
    }
}