import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

final int SCREEN_HEIGHT = 1080;
final int SCREEN_WIDTH = SCREEN_HEIGHT * 16 / 9;

void main() {
    InitWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Collar");
    SetTargetFPS(GetMonitorRefreshRate(1));
    // Calcular el radio máximo de la Esfera externa
    int MAX_EXTERNAL_RADIUS = Math.min(SCREEN_WIDTH, SCREEN_HEIGHT) / 2;
    int numPearls = 32;
    float pearlRadiusCollarRadiusRatio = (float) Math.sin(Math.PI / numPearls);
    float collarExtRadius = MAX_EXTERNAL_RADIUS * 1f;
    float collarRadius = collarExtRadius / (1 + pearlRadiusCollarRadiusRatio);
    float pearlRadius = collarRadius * pearlRadiusCollarRadiusRatio;
    float collarCenterX = SCREEN_WIDTH / 2;
    float collarCenterY = SCREEN_HEIGHT / 2;
    float angle = 360f / numPearls;
    float angleRadians = (float) Math.toRadians(angle);
    while (!WindowShouldClose()) {
        BeginDrawing();
        ClearBackground(DARKGRAY);
        for (int i = 0; i < numPearls; i++) {
            float pearlCenterX = (float) (collarCenterX + collarRadius * Math.cos(angleRadians * i));
            float pearlCenterY = (float) (collarCenterY - collarRadius * Math.sin(angleRadians * i));
            DrawCircle((int) pearlCenterX, (int) pearlCenterY, pearlRadius, RAYWHITE);
        }
        EndDrawing();
    }
    CloseWindow();
}