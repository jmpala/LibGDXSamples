package io.jmpalazzolo.libgdxsamples.systems.render;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.jmpalazzolo.libgdxsamples.Components.Playable;
import io.jmpalazzolo.libgdxsamples.Components.Position;
import io.jmpalazzolo.libgdxsamples.MyGame;

/**
 * This system process entities with
 * {@link Playable} and {@link Position}
 * components
 *
 * With that information, the system draws the player
 */
@All({Playable.class, Position.class})
public class PlayerRenderSystem extends IteratingSystem {

    private ComponentMapper<Position> mPosition;

    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private Viewport viewport;

    public PlayerRenderSystem(MyGame game) {
        shapeRenderer = game.getRenderer();
        camera = game.getCamera();
        viewport = game.getViewport();
    }

    @Override
    protected void process(int entityId) {
        Position position = mPosition.get(entityId);

        camera.update();
        viewport.apply();
        shapeRenderer.setProjectionMatrix(camera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        shapeRenderer.circle(position.position.x, position.position.y, 5);
        camera.position.x = position.position.x;
        camera.position.y = position.position.y;
        camera.update();

        shapeRenderer.end();
    }

}
