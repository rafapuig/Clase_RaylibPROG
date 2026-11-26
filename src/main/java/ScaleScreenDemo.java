import static com.raylib.Colors.*;
import static com.raylib.Helpers.newRectangle;
import static com.raylib.Raylib.*;

final int VIRTUAL_HEIGHT = 600;
final int VIRTUAL_WIDTH = 800;

void main() {
    InitWindow(1920, 1080, "Scale Demo");

    RenderTexture virtualScreen = LoadRenderTexture(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);

    while (!WindowShouldClose()) {
        BeginTextureMode(virtualScreen);
        ClearBackground(SKYBLUE);
        DrawRectangle(
                (VIRTUAL_WIDTH - 100) / 2,
                (VIRTUAL_HEIGHT - 100) / 2,
                100, 100,
                WHITE
        );
        EndTextureMode();

        float scale = Math.min(
                GetScreenWidth() / VIRTUAL_WIDTH,
                GetScreenHeight() / VIRTUAL_HEIGHT
        );

        float width = VIRTUAL_WIDTH * scale;
        float height = VIRTUAL_HEIGHT * scale;

        float x = (GetScreenWidth() - width) / 2;
        float y = (GetScreenHeight() - height) / 2;

        BeginDrawing();
        ClearBackground(BLACK);
        DrawTexturePro(
                virtualScreen.texture(),
                newRectangle(0, 0, VIRTUAL_WIDTH, VIRTUAL_HEIGHT),
                newRectangle(x, y, width, height),
                Vector2Zero(),
                0f,
                WHITE
        );
        EndDrawing();
    }
    CloseWindow();

}