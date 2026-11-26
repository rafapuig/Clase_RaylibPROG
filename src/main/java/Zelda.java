import static com.raylib.Colors.*;
import static com.raylib.Helpers.newRectangle;
import static com.raylib.Helpers.newVector2;
import static com.raylib.Raylib.*;

final float SCALE = 4f;
final int TILE_SIZE = 16;

final int TILE_ROWS = 18;
final int TILE_COLS = TILE_ROWS * 16 / 9;

final int CHARACTER_SPRITE_OFFSET_Y = 5;
final int CHARACTER_SPRITE_WIDTH = TILE_SIZE;
final int CHARACTER_SPRITE_HEIGHT = TILE_SIZE * 3 / 2;

final float CHARACTER_SPEED = 150 * SCALE;

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
            0, 0,
            CHARACTER_SPRITE_WIDTH * SCALE, CHARACTER_SPRITE_HEIGHT * SCALE
    );

    Rectangle grassSprite = newRectangle(0, 0, TILE_SIZE, TILE_SIZE);
    Rectangle houseFrontSprite = newRectangle(TILE_SIZE * 6, 0, TILE_SIZE * 5, TILE_SIZE * 5);

    Rectangle tile = newRectangle(0, 0, TILE_SIZE * SCALE, TILE_SIZE * SCALE);


    Vector2 housePosition = newVector2(TILE_SIZE * 5 * SCALE, TILE_SIZE * 5 * SCALE);
    Rectangle house = newRectangle(
            housePosition.x(), housePosition.y(),
            houseFrontSprite.width() * SCALE,
            houseFrontSprite.height() * SCALE
    );
    Vector2 houseAxis = newVector2(house.width() / 2, house.height() / 2);


    while (!WindowShouldClose()) {

        Vector2 input = Vector2Zero();

        if (IsKeyDown(KEY_UP)) {
            input.y(input.y() - 1);
        }
        if (IsKeyDown(KEY_DOWN)) {
            input.y(input.y() + 1);
        }
        if (IsKeyDown(KEY_LEFT)) {
            input.x(input.x() - 1);
        }
        if (IsKeyDown(KEY_RIGHT)) {
            input.x(input.x() + 1);
        }

        Vector2 direction = Vector2Normalize(input);

        player.x(player.x() + direction.x() * CHARACTER_SPEED * GetFrameTime());
        player.y(player.y() + direction.y() * CHARACTER_SPEED * GetFrameTime());

        if (player.x() < 0) {
            player.x(0);
        } else if (player.x() + player.width() > GetScreenWidth()) {
            player.x(GetScreenWidth() - player.width());
        }
        if (player.y() < 0) {
            player.y(0);
        } else if (player.y() + player.height() > GetScreenHeight()) {
            player.y(GetScreenHeight() - player.height());
        }

        BeginDrawing();
        ClearBackground(DARKBROWN);

        for (int i = 0; i < TILE_ROWS; i++) {
            for (int j = 0; j < TILE_COLS; j++) {
                tile.x(j * TILE_SIZE * SCALE);
                tile.y(i * TILE_SIZE * SCALE);
                DrawTexturePro(overworldTexture, grassSprite, tile, Vector2Zero(), 0f, WHITE);
            }
        }

        // Dibuja el jugador
        DrawTexturePro(characterTexture, playerSprite, player, Vector2Zero(), 0f, WHITE);

        // Dibujar la casa
        DrawTexturePro(overworldTexture, houseFrontSprite, house, Vector2Zero(), 0f, WHITE);


        EndDrawing();
    }
    // Descargar la textura de la GPU
    UnloadTexture(overworldTexture);
    UnloadTexture(characterTexture);
    CloseWindow();
}