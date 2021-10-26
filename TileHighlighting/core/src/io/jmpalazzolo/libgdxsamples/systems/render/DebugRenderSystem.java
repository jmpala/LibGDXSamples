package io.jmpalazzolo.libgdxsamples.systems.render;

import com.artemis.BaseSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.jmpalazzolo.libgdxsamples.Constants;
import io.jmpalazzolo.libgdxsamples.MyGame;
import org.pmw.tinylog.Logger;

/**
 * This system draws the debug boundaries of the elements on the screen
 */
public class DebugRenderSystem extends BaseSystem implements InputProcessor {

    private ShapeRenderer shapeRenderer;

    private OrthographicCamera camera;
    private Viewport viewport;
    private int tileSize;

    private int mousePositionX = 0;
    private int mousePositionY = 0;


    public DebugRenderSystem(MyGame game) {
        shapeRenderer = game.getRenderer();
        camera = game.getCamera();
        viewport = game.getViewport();
        tileSize = Constants.TILE_SIZE;
        Gdx.input.setInputProcessor(this);
    }

    @Override
    protected void processSystem() {
        camera.update();
        viewport.apply();
        shapeRenderer.setProjectionMatrix(camera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        drawGrid();
        drawCenter();
        drawMapTopAndRightLimits();
        drawOrigin();
        captureInputToMoveCamera();
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        drawHighlightedTile();
        shapeRenderer.end();
    }

    private void drawHighlightedTile() {
        int size = Constants.TILE_SIZE;
        shapeRenderer.setColor(Color.GOLD);
        shapeRenderer.rect(mousePositionX * size, mousePositionY * size, size, size);
    }

    private void drawGrid() {
        shapeRenderer.setColor(Color.WHITE);
        for(int x = 0; x < Constants.HARDCODED_MAP_WIDTH; x += tileSize) {
            shapeRenderer.line(x,0, x, Constants.HARDCODED_MAP_WIDTH);
        }

        for(int y = 0; y < Constants.HARDCODED_MAP_HEIGHT; y += tileSize) {
            shapeRenderer.line(0,y, Constants.HARDCODED_MAP_HEIGHT, y);
        }
    }

    private void drawCenter() {
        shapeRenderer.setColor(Color.PINK);
        shapeRenderer.line(Constants.HARDCODED_MAP_WIDTH / 2,0, Constants.HARDCODED_MAP_WIDTH / 2, Constants.HARDCODED_MAP_HEIGHT);
        shapeRenderer.line(0,Constants.HARDCODED_MAP_HEIGHT / 2, Constants.HARDCODED_MAP_WIDTH, Constants.HARDCODED_MAP_HEIGHT / 2);
    }

    private void drawMapTopAndRightLimits() {
        shapeRenderer.setColor(Color.YELLOW);
        shapeRenderer.line(0,Constants.HARDCODED_MAP_HEIGHT, Constants.HARDCODED_MAP_WIDTH, Constants.HARDCODED_MAP_HEIGHT);
        shapeRenderer.line(Constants.HARDCODED_MAP_WIDTH,0,Constants.HARDCODED_MAP_WIDTH, Constants.HARDCODED_MAP_HEIGHT);
    }

    private void drawOrigin() {
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.line(0,0, 0, Constants.HARDCODED_MAP_HEIGHT);
        shapeRenderer.line(0,0,Constants.HARDCODED_MAP_WIDTH, 0);
    }

    private void captureInputToMoveCamera() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            camera.position.y++;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            camera.position.y--;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.position.x--;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            camera.position.x++;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            camera.zoom += 0.1f;
        } else if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            camera.zoom -= 0.1f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
            camera.position.x = Constants.HARDCODED_MAP_WIDTH / 2;
            camera.position.y = Constants.HARDCODED_MAP_HEIGHT / 2;
            camera.zoom = 1;
        }
        camera.update();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Vector3 worldPositionInWorld = camera.unproject(new Vector3(screenX, screenY, 0));
        int xCandidate = MathUtils.floor(worldPositionInWorld.x / Constants.TILE_SIZE);
        int yCandidate = MathUtils.floor(worldPositionInWorld.y / Constants.TILE_SIZE);
        mousePositionX = MathUtils.clamp(xCandidate, 0, 9);
        mousePositionY = MathUtils.clamp(yCandidate, 0, 9);
        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
