package io.jmpalazzolo.libgdxsamples.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import io.jmpalazzolo.libgdxsamples.Components.Position;
import io.jmpalazzolo.libgdxsamples.Components.movement.MovementDirection;
import io.jmpalazzolo.libgdxsamples.Constants;

/**
 * This system process entities with
 * {@link Position} and {@link MovementDirection}
 * components
 *
 * It gets the {@link Position} and {@link MovementDirection} components
 * for the current entity.
 *
 * With that information, change the X and Y position of the entity.
 */
@All({Position.class, MovementDirection.class})
public class MovementSystem extends IteratingSystem {

    private ComponentMapper<MovementDirection> mMovementDirection;
    private ComponentMapper<Position> mPosition;

    @Override
    protected void process(int entityId) {
        MovementDirection direction = mMovementDirection.get(entityId);
        Position pos = mPosition.get(entityId);
        float delta = Gdx.graphics.getDeltaTime();

        switch (direction.direction) {
            case NORTH:
                pos.position.y += Constants.MOVEMENT_SPEED * delta;
                break;
            case NORTH_EAST:
                pos.position.y += Constants.MOVEMENT_SPEED * delta;
                pos.position.x += Constants.MOVEMENT_SPEED * delta;
                break;
            case EAST:
                pos.position.x += Constants.MOVEMENT_SPEED * delta;
                break;
            case SOUTH_EAST:
                pos.position.y -= Constants.MOVEMENT_SPEED * delta;
                pos.position.x += Constants.MOVEMENT_SPEED * delta;
                break;
            case SOUTH:
                pos.position.y -= Constants.MOVEMENT_SPEED * delta;
                break;
            case SOUTH_WEST:
                pos.position.y -= Constants.MOVEMENT_SPEED * delta;
                pos.position.x -= Constants.MOVEMENT_SPEED * delta;
                break;
            case WEST:
                pos.position.x -= Constants.MOVEMENT_SPEED * delta;
                break;
            case NORTH_WEST:
                pos.position.y += Constants.MOVEMENT_SPEED * delta;
                pos.position.x -= Constants.MOVEMENT_SPEED * delta;
                break;
        }
    }

}
