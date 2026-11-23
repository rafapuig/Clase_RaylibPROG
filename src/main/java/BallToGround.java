import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

final int SCREEN_HEIGHT = 1080;
final int SCREEN_WIDTH = SCREEN_HEIGHT * 16 / 9;

final float BALL_RADIUS = 30.0f;

final float GRAVITY = 9.81f * 300; // pixels / s^2

void main() {
    SetConfigFlags(FLAG_VSYNC_HINT);
    InitWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Bola que cae");

    float ballX = (float) (SCREEN_WIDTH / 2);
    float ballY = (float) (SCREEN_HEIGHT / 2);

    float ballVelocityX = 0;
    float ballVelocityY = 0;

    boolean isStarted = false;

    while (!WindowShouldClose()) {
        /** Manejar la entrada */
        if (IsKeyPressed(KEY_SPACE)) {
            isStarted = true;
        }

        if (IsMouseButtonDown(MOUSE_BUTTON_LEFT)) {
            float targetX = GetMouseX();
            float targetY = GetMouseY();

            if (targetY < ballY) {
                float time = (float) Math.sqrt(
                        (2 * (ballY - targetY)) / GRAVITY
                );
                ballVelocityY = -GRAVITY * time;
                ballVelocityX = (targetX - ballX) / time;
            }
        }

        /** Actualizar las variables */
        // Actualizar la posición de la bola
        if (isStarted) {
            ballX += ballVelocityX * GetFrameTime();
            ballY += ballVelocityY * GetFrameTime();

            if (ballY + BALL_RADIUS > SCREEN_HEIGHT && ballVelocityY > 0 ||
                ballY - BALL_RADIUS < 0 && ballVelocityY < 0) {
                ballVelocityY = -ballVelocityY;
            } else {
                ballVelocityY += GRAVITY * GetFrameTime();
            }
        }

        if (ballX + BALL_RADIUS > SCREEN_WIDTH && ballVelocityX > 0 ||
            ballX - BALL_RADIUS < 0 && ballVelocityX < 0) {
            ballVelocityX = -ballVelocityX;
        }


        /** Dibujar el frame */
        BeginDrawing();
        ClearBackground(DARKBLUE);

        // Dibuja la bola
        DrawCircle((int) ballX, (int) ballY, BALL_RADIUS, ORANGE);

        DrawFPS(20, 20);
        EndDrawing();
    }
    CloseWindow();
}