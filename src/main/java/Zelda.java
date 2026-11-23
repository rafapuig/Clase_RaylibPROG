import static com.raylib.Colors.*;
import static com.raylib.Helpers.newRectangle;
import static com.raylib.Helpers.newVector2;
import static com.raylib.Raylib.*;

final float SCALE = 4f;
final int TILE_SIZE = 16;

final int SCREEN_HEIGHT = 1080;
final int SCREEN_WIDTH = SCREEN_HEIGHT * 16 / 9;

void main() {
    //SetConfigFlags(FLAG_FULLSCREEN_MODE);
    InitWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Zelda");
    SetTargetFPS(GetMonitorRefreshRate(1));

    // Cargar textura en la GPU
    Texture overworldTexture = LoadTexture("assets/Overworld.png");

    Rectangle grassSprite = newRectangle(0, 0, TILE_SIZE, TILE_SIZE);
    Rectangle houseFrontSprite = newRectangle( TILE_SIZE * 6, 0, TILE_SIZE * 5, TILE_SIZE * 5);

    int posX = (GetScreenWidth() - overworldTexture.width()) / 2;
    int posY = (GetScreenHeight() - overworldTexture.height()) / 2;

    Vector2 position = newVector2(TILE_SIZE * 2, TILE_SIZE * 2);
    Rectangle house = newRectangle(
            position.x(), position.y(),
            houseFrontSprite.width() * SCALE,
            houseFrontSprite.height() * SCALE
    );

    while (!WindowShouldClose()) {
        BeginDrawing();
        ClearBackground(RAYWHITE);

        //DrawTexture(overworldTexture, posX, posY, WHITE);
        //DrawTextureV(overworldTexture, position, WHITE);
        //DrawTextureEx(overworldTexture, position, 0f, SCALE, WHITE);
        //DrawTextureRec(overworldTexture, houseFrontSprite, position, WHITE);
        DrawTexturePro(overworldTexture, houseFrontSprite, house, Vector2Zero(), 0f, WHITE);

        EndDrawing();
    }
    // Descargar la textura de la GPU
    UnloadTexture(overworldTexture);
    CloseWindow();
}