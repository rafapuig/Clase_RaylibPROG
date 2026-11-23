import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

final int SCREEN_HEIGHT = 1080;
final int SCREEN_WIDTH = SCREEN_HEIGHT * 16 / 9;

final float MAX_PEARL_RADIUS = (float) (SCREEN_HEIGHT / 2);
final float MIN_PEARL_RADIUS = 10f;

final float INITIAL_PEARL_RADIUS = (float) (SCREEN_HEIGHT / 15);
final float PEARL_RADIUS_GROWTH_FACTOR = .5f;

void main() {
    InitWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Collar de perlas");
    SetTargetFPS(GetMonitorRefreshRate(1));

    float pearlRadius = INITIAL_PEARL_RADIUS;

    while (!WindowShouldClose()) {
        if(IsKeyDown(KEY_UP)) {
            pearlRadius *= 1 + PEARL_RADIUS_GROWTH_FACTOR * GetFrameTime();
            pearlRadius = Math.min(pearlRadius, MAX_PEARL_RADIUS);
        }
        if(IsKeyDown(KEY_DOWN)) {
            pearlRadius /= 1 + PEARL_RADIUS_GROWTH_FACTOR * GetFrameTime();
            pearlRadius = Math.max(pearlRadius, MIN_PEARL_RADIUS);
        }

        // Calcular cantidad de perlas que caben en la pantalla
        final float pearlDiameter = 2 * pearlRadius;
        final int numPearls = (int) (SCREEN_WIDTH / pearlDiameter);

        // Calcular el espacio sobrante de pantalla en horizontal
        final float totalMargin = SCREEN_WIDTH - pearlDiameter * numPearls;

        // Calcular el margen izquierdo
        final float leftMargin = totalMargin / 2;

        for (int i = 0; i < numPearls; i++) {
            final float pearlX = leftMargin + pearlRadius + pearlDiameter * i;
            final int pearlY = SCREEN_HEIGHT / 2;

            // Dibujar la perla i-esima
            DrawCircle((int) pearlX, pearlY, pearlRadius, RAYWHITE);
        }

        BeginDrawing();
        ClearBackground(BLACK);
        EndDrawing();
    }

    CloseWindow();
}