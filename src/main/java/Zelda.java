import static com.raylib.Colors.*;
import static com.raylib.Helpers.newVector2;
import static com.raylib.Raylib.*;

final int SCREEN_HEIGHT = 1080;
final int SCREEN_WIDTH = SCREEN_HEIGHT * 16 / 9;

void main() {
    //SetConfigFlags(FLAG_FULLSCREEN_MODE);
    InitWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Zelda");
    SetTargetFPS(GetMonitorRefreshRate(1));

    // Cargar textura en la GPU
    Texture overworldTexture = LoadTexture("assets/Overworld.png");

    int posX = (GetScreenWidth() - overworldTexture.width()) / 2;
    int posY = (GetScreenHeight() - overworldTexture.height()) / 2;

    Vector2 position = newVector2(posX, posY);

    while (!WindowShouldClose()) {
        BeginDrawing();
        ClearBackground(RAYWHITE);

        //DrawTexture(overworldTexture, posX, posY, WHITE);
        //DrawTextureV(overworldTexture, position, WHITE);
        DrawTextureEx(overworldTexture, position, 0f, 2f, WHITE);

        EndDrawing();
    }
    // Descargar la textura de la GPU
    UnloadTexture(overworldTexture);
    CloseWindow();
}