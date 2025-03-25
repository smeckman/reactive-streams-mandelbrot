# Mandelbrot Fractal Viewer

## Overview

This Spring Boot application renders the Mandelbrot set using reactive programming and WebSocket technology, providing a dynamic, real-time fractal visualization.

## Features

- Reactive Mandelbrot set generation
- Real-time WebSocket data streaming
- Progressive fractal rendering
- HTML5 Canvas visualization

## Technology Stack

- Backend: Spring Boot
- Reactive Programming: Project Reactor
- WebSocket Communication
- Frontend: Vanilla JavaScript
- Rendering: HTML5 Canvas

## Prerequisites

- Java 17+
- Gradle
- Modern web browser

## Project Structure

```
└── src
    ├── main
    │   ├── java
    │   │   └── org/example
    │   │       ├── config
    │   │       │   └── WebSocketConfig.java
    │   │       ├── handler
    │   │       │   └── MandelbrotWebSocketHandler.java
    │   │       ├── producer
    │   │       │   └── Mandelbrot.java
    │   │       └── Application.java
    │   └── resources
    │       └── static
    │           └── index.html
```

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/smeckman/reactive-streams-mandelbrot.git
   cd reactive-streams-mandelbrot
   ```

2. Build the project:
   ```bash
   gradle clean install
   ```

3. Run the application:
   ```bash
   gradle bootRun
   ```

4. Open `http://localhost:8080` in your web browser

## Configuration

### Fractal Parameters

You can modify fractal rendering by adjusting these constants in `Mandelbrot.java`:

- `WIDTH`: Image width (default: 800)
- `HEIGHT`: Image height (default: 800)
- `X_MIN`, `X_MAX`: Real axis range
- `Y_MIN`, `Y_MAX`: Imaginary axis range
- `MAX_ITER`: Maximum iteration count

## How It Works

1. The server generates Mandelbrot set data using a reactive stream
2. WebSocket transmits pixel data to the client
3. JavaScript renders pixels on an HTML5 Canvas
4. Progressive rendering creates a dynamic visualization

## Performance

- Uses Project Reactor for non-blocking computation
- Concurrent pixel calculation
- Configurable rendering delay

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## Acknowledgments

- Inspired by the mathematical beauty of the Mandelbrot set
- Powered by Spring Boot and Project Reactor
