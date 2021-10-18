package io.jmpalazzolo.libgdxsamples.systems.input;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import io.jmpalazzolo.libgdxsamples.Components.Playable;
import io.jmpalazzolo.libgdxsamples.Components.Position;
import io.jmpalazzolo.libgdxsamples.Components.movement.MovementDirection;
import io.jmpalazzolo.libgdxsamples.systems.input.states.InputState;
import io.jmpalazzolo.libgdxsamples.systems.input.states.StandingState;

/**
 * This system process entities with
 * {@link Playable} and {@link Position}
 * components
 *
 * Captures the input and segregates the behavior in swappable classes.
 *
 * Please see the gang of four "state pattern"
 * - context: this system
 * - state: {@link io.jmpalazzolo.libgdxsamples.systems.input.states.InputState}
 *
 * Possible states:
 * - {@link io.jmpalazzolo.libgdxsamples.systems.input.states.StandingState}
 * - {@link io.jmpalazzolo.libgdxsamples.systems.input.states.WalkingState}
 * - {@link io.jmpalazzolo.libgdxsamples.systems.input.states.MeditatingState}
 *
 */
@All({Playable.class, Position.class})
public class InputSystem extends IteratingSystem {

    private ComponentMapper<MovementDirection> mMovementDirection;

    private InputState currentState;
    private int entity;

    public InputSystem() {
        currentState = StandingState.getINSTANCE(this);
    }

    @Override
    protected void process(int entityId) {
        entity = entityId;
    }

    public void setCurrentState(InputState currentState) {
        this.currentState = currentState;
    }

    public ComponentMapper<MovementDirection> getmMovementDirection() {
        return mMovementDirection;
    }

    public int getEntity() {
        return entity;
    }
}
