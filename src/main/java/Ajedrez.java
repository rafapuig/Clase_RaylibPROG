import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

final int TILE_SIZE = 128;
final int NUM_ROWS = 8;
final int NUM_COLS = 8;

final int SCREEN_WIDTH = TILE_SIZE * NUM_COLS;
final int SCREEN_HEIGHT = TILE_SIZE * NUM_ROWS;

void main() {
    InitWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Ajedrez");
    SetTargetFPS(GetMonitorRefreshRate(1));

    while (!WindowShouldClose()) {
        BeginDrawing();
        ClearBackground(SKYBLUE);

        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                DrawRectangle(
                        TILE_SIZE * j, TILE_SIZE * i,
                        TILE_SIZE, TILE_SIZE,
                        (i + j) % 2 == 0 ? RAYWHITE : GRAY
                );

                int fontSize = (int) (TILE_SIZE * 0.8);
                String text = String.format("%d", (i+j));
                int textWidth = MeasureText(text, fontSize);
                int posX = (TILE_SIZE - textWidth) / 2 + TILE_SIZE * j;
                int posY = (TILE_SIZE - fontSize) / 2 + TILE_SIZE * i;

                DrawText(
                        text,
                        posX, posY,
                        fontSize,
                        LIGHTGRAY
                );
            }
        }

        EndDrawing();
    }

    CloseWindow();
}