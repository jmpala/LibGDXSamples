package io.jmpalazzolo.libgdxsamples.systems.input;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import io.jmpalazzolo.libgdxsamples.Components.Playable;
import io.jmpalazzolo.libgdxsamples.Components.Position;
import io.jmpalazzolo.libgdxsamples.Components.movement.MovementDirection;
import io.jmpalazzolo.libgdxsamples.systems.input.states.InputState;
import io.jmpalazzolo.libgdxsamples.systems.input.states.StandingState;

@All({Playable.class, Position.class})
public class InputSystem extends IteratingSystem {

    private ComponentMapper<MovementDirection> mMovementDirection;

    private InputState currentState;
    private MovementDirection direction;
    private int entity;

    public InputSystem() {
        currentState = StandingState.getINSTANCE(this);
    }

    @Override
    protected void process(int entityId) {
        direction = mMovementDirection.get(entityId);
        entity = entityId;
    }

    public void setCurrentState(InputState currentState) {
        this.currentState = currentState;
    }

    public ComponentMapper<MovementDirection> getmMovementDirection() {
        return mMovementDirection;
    }

    public MovementDirection getPlayablePosition() {
        return direction;
    }

    public int getEntity() {
        return entity;
    }
}
