import static com.raylib.Colors.*;
import static com.raylib.Helpers.newRectangle;
import static com.raylib.Helpers.newVector2;
import static com.raylib.Raylib.*;

final float SCALE = 5f;
final int TILE_SIZE = 16;

final int CHARACTER_SPRITE_OFFSET_Y = 5;
final int CHARACTER_SPRITE_WIDTH = TILE_SIZE;
final int CHARACTER_SPRITE_HEIGHT = TILE_SIZE * 3 / 2;

final int SCREEN_HEIGHT = 1080;
final int SCREEN_WIDTH = SCREEN_HEIGHT * 16 / 9;

void main() {
    //SetConfigFlags(FLAG_FULLSCREEN_MODE);
    InitWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Zelda");
    SetTargetFPS(GetMonitorRefreshRate(1));

    // Cargar textura en la GPU
    Texture overworldTexture = LoadTexture("assets/Overworld.png");
    Texture characterTexture = LoadTexture("assets/character.png");

    Rectangle playerSprite = newRectangle(
            0, CHARACTER_SPRITE_OFFSET_Y,
            CHARACTER_SPRITE_WIDTH, CHARACTER_SPRITE_HEIGHT
    );
    Rectangle player = newRectangle(
            0,0,
            CHARACTER_SPRITE_WIDTH * SCALE, CHARACTER_SPRITE_HEIGHT * SCALE
    );

    Rectangle grassSprite = newRectangle(0, 0, TILE_SIZE, TILE_SIZE);
    Rectangle houseFrontSprite = newRectangle(TILE_SIZE * 6, 0, TILE_SIZE * 5, TILE_SIZE * 5);

    int posX = (GetScreenWidth() - overworldTexture.width()) / 2;
    int posY = (GetScreenHeight() - overworldTexture.height()) / 2;

    Vector2 position = newVector2(TILE_SIZE * 5 * SCALE, TILE_SIZE * 5 * SCALE);
    Rectangle house = newRectangle(
            position.x(), position.y(),
            houseFrontSprite.width() * SCALE,
            houseFrontSprite.height() * SCALE
    );
    Vector2 houseAxis = newVector2(house.width() / 2, house.height() / 2);

    float angle = 0f;

    while (!WindowShouldClose()) {

        angle += 180 * GetFrameTime();

        house.x(house.x() + 300 * GetFrameTime());

        BeginDrawing();
        ClearBackground(DARKBROWN);

        //DrawTexture(overworldTexture, posX, posY, WHITE);
        //DrawTextureV(overworldTexture, position, WHITE);
        //DrawTextureEx(overworldTexture, position, 0f, SCALE, WHITE);
        //DrawTextureRec(overworldTexture, houseFrontSprite, position, WHITE);

        //DrawTexturePro(overworldTexture, houseFrontSprite, house, houseAxis, angle, WHITE);
        DrawTexturePro(characterTexture, playerSprite, player, Vector2Zero(), 0f, WHITE);

        EndDrawing();
    }
    // Descargar la textura de la GPU
    UnloadTexture(overworldTexture);
    UnloadTexture(characterTexture);
    CloseWindow();
}