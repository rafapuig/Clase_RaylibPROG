import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

final int SCREEN_HEIGHT = 1080;
final int SCREEN_WIDTH = SCREEN_HEIGHT * 16 / 9;

final float BALL_RADIUS = 30f;

void main() {
    InitWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Bola hacia la derecha");
    SetTargetFPS(GetMonitorRefreshRate(1));
    //SetTargetFPS(120);

    float ballX = (float) (SCREEN_WIDTH / 2);
    float ballY = (float) (SCREEN_HEIGHT / 2);
    float ballSpeed = 20f * 60; // pixels por segundo // pixels por frame

    float elapsedTime = 0;
    float totalTime = -1;

    float originX = 0;
    float originY = 0;

    int targetX = 0;
    int targetY = 0;

    while (!WindowShouldClose()) {

        // Input
        if (IsMouseButtonDown(MOUSE_BUTTON_LEFT)) {
            // Inicial el cronometro
            elapsedTime = 0;
            // Acordarme de donde estaba la bola cuando se hizo click
            originX = ballX;
            originY = ballY;

            // Apuntar a donde tengo que ir
            targetX = GetMouseX();
            targetY = GetMouseY();

            // Calcula la distancia
            float distance = (float) Math.sqrt(
                    Math.pow(targetX - originX, 2) +
                    Math.pow(targetY - originY, 2)
            );

            // Calcula el tiempo total
            totalTime = distance / ballSpeed;
        }

        if (elapsedTime <= totalTime) {

            float percent = (float) elapsedTime / totalTime;

            ballX = originX + (targetX - originX) * percent;
            ballY = originY + (targetY - originY) * percent;

            // Actualizar el cronometro
            elapsedTime += GetFrameTime();
        }


        // Update
        //ballX += ballSpeed * GetFrameTime();
        //ballY = ballY + ballSpeed * GetFrameTime();

        if (ballX + BALL_RADIUS > SCREEN_WIDTH || ballX - BALL_RADIUS < 0) {
            ballSpeed = -ballSpeed;
        }

        /* estamos en el borde derecho */
        if (ballX + BALL_RADIUS > SCREEN_WIDTH) {
            // Ponemos la bola justo tocando el borde derecho
            ballX = SCREEN_WIDTH - BALL_RADIUS;

            // Cambiamos la dirección de la bola
            //ballSpeed = -ballSpeed;
        }

        // Comprobar si me salgo por el borde izquierdo
        if (ballX - BALL_RADIUS < 0) {
            ballX = BALL_RADIUS;
            //ballSpeed *= -1;
        }


        // Draw
        BeginDrawing();
        ClearBackground(BLACK);

        DrawFPS(20, 20);

        /*final String text = "Hola Raylib";
        final int textFontSize = 60;
        final int textWidth = MeasureText(text, textFontSize);

        DrawText(text,
                (SCREEN_WIDTH - textWidth) / 2,
                (SCREEN_HEIGHT - textFontSize) / 2,
                textFontSize, RAYWHITE);*/

        DrawCircle((int) ballX, (int) ballY, BALL_RADIUS, ORANGE);

        DrawText(
                String.format("%.2f", elapsedTime),
                30,
                30,
                80,
                WHITE
        );

        EndDrawing();
    }
    CloseWindow();
}