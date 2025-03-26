package org.example.producer;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Mandelbrot {
    // Image dimensions
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    // Mandelbrot computation parameters
    private static final double X_MIN = -2.0;
    private static final double X_MAX = 1.0;
    private static final double Y_MIN = -1.5;
    private static final double Y_MAX = 1.5;
    private static final int MAX_ITER = 1000; // Maximum iterations for set convergence

    public static Flux<String> generateData() {
        // Reactive stream to generate Mandelbrot data
        return Flux.range(0, HEIGHT) // Rows of the image
                .flatMap(y -> Flux.range(0, WIDTH) // Columns of the image
                        .map(x -> computeMandelbrot(x, y))
                        .subscribeOn(Schedulers.boundedElastic())
                        .publishOn(Schedulers.parallel())
                )
                // Simulate progressive rendering (remove for faster performance)
                .delayElements(Duration.ofMillis(new Random().nextInt(50)));
    }

    // Compute a single Mandelbrot set point
    static String computeMandelbrot(int x, int y) {
        // Convert pixel (x, y) to complex number (real and imaginary parts)
        double real = X_MIN + (x * (X_MAX - X_MIN) / WIDTH);
        double imaginary = Y_MIN + (y * (Y_MAX - Y_MIN) / HEIGHT);

        double zr = 0.0; // Z(real) part
        double zi = 0.0; // Z(imaginary) part
        int iterations = 0;

        // Iterate Mandelbrot formula: Z(n+1) = Z(n)^2 + C
        while (zr * zr + zi * zi <= 4.0 && iterations < MAX_ITER) {
            double temp = zr * zr - zi * zi + real;
            zi = 2.0 * zr * zi + imaginary;
            zr = temp;
            iterations++;
        }

        // Convert the result to string format for WebSocket transmission
        // E.g., "x,y,iterations" for later rendering
        return x + "," + y + "," + iterations;
    }
}