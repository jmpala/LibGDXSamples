package io.jmpalazzolo.libgdxsamples.systems;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.badlogic.gdx.math.Vector2;
import io.jmpalazzolo.libgdxsamples.Components.Playable;
import io.jmpalazzolo.libgdxsamples.Components.Position;
import io.jmpalazzolo.libgdxsamples.Constants;
import org.pmw.tinylog.Logger;

public class EntityFactorySystem extends BaseSystem {

    private ComponentMapper<Playable> mPlayable;
    private ComponentMapper<Position> mPosition;

    @Override
    protected void initialize() {
        createPlayer();
    }

    @Override
    protected void processSystem() {

    }

    public void createPlayer() {
        Logger.info("Creating Player...");
        int player = world.create();
        mPlayable.create(player);
        Position position = mPosition.create(player);
        position.position = new Vector2();
        position.position.x = Constants.VIEWPORT_WIDTH / 2;
        position.position.y = Constants.VIEWPORT_HEIGHT / 2;
    }
}
