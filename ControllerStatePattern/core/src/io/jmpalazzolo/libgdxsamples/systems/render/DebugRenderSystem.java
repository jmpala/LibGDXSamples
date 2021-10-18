package io.jmpalazzolo.libgdxsamples.systems.render;

import com.artemis.BaseSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.jmpalazzolo.libgdxsamples.Constants;
import io.jmpalazzolo.libgdxsamples.MyGame;

public class DebugRenderSystem extends BaseSystem {

    private ShapeRenderer shapeRenderer;

    private OrthographicCamera camera;
    private Viewport viewport;
    private int tileSize;

    public DebugRenderSystem(MyGame game) {
        shapeRenderer = game.getRenderer();
        camera = game.getCamera();
        viewport = game.getViewport();
        tileSize = Constants.TILE_SIZE;
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
        shapeRenderer.end();
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



}
