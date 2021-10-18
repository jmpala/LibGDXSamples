package io.jmpalazzolo.libgdxsamples.systems.input.states;

import com.artemis.ComponentMapper;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import io.jmpalazzolo.libgdxsamples.Components.movement.Direction;
import io.jmpalazzolo.libgdxsamples.Components.movement.MovementDirection;
import io.jmpalazzolo.libgdxsamples.systems.input.InputSystem;

/**
 * Abstract class that implements {@link InputProcessor} in order to
 * manage the player's input.
 *
 * Handles the {@link InputSystem} and the state of the keys, as also
 * adds and removes the {@link MovementDirection} component from the processed
 * entity on {@link InputSystem}.
 *
 */
public abstract class InputState implements InputProcessor {

    protected InputSystem inputSystem;

    protected boolean isWPressed;
    protected boolean isDPressed;
    protected boolean isSPressed;
    protected boolean isAPressed;

    public InputState() {
        this.isWPressed = false;
        this.isDPressed = false;
        this.isSPressed = false;
        this.isAPressed = false;
    }

    public InputState(InputSystem inputSystem) {
        this();
        this.inputSystem = inputSystem;
    }

    abstract void stand();

    abstract void walk();

    abstract void meditate();

    protected void getMovementDirectionOnKeyDown(int keycode) {

        ComponentMapper<MovementDirection> mMovementDirection = inputSystem.getmMovementDirection();
        MovementDirection movementDir = mMovementDirection.get(inputSystem.getEntity());

        if(Input.Keys.W == keycode) {
            movementDir = mMovementDirection.create(inputSystem.getEntity());
            movementDir.direction = Direction.NORTH;
            if(isAPressed) movementDir.direction = Direction.NORTH_WEST;
            if(isDPressed) movementDir.direction = Direction.NORTH_EAST;
            isWPressed = true;
        } else if (Input.Keys.S == keycode) {
            movementDir = mMovementDirection.create(inputSystem.getEntity());
            movementDir.direction = Direction.SOUTH;
            if(isAPressed) movementDir.direction = Direction.SOUTH_WEST;
            if(isDPressed) movementDir.direction = Direction.SOUTH_EAST;
            isSPressed = true;
        }
        if(Input.Keys.A == keycode) {
            movementDir = mMovementDirection.create(inputSystem.getEntity());
            movementDir.direction = Direction.WEST;
            if(isWPressed) movementDir.direction = Direction.NORTH_WEST;
            if(isSPressed) movementDir.direction = Direction.SOUTH_WEST;
            isAPressed = true;
        } else if (Input.Keys.D == keycode) {
            movementDir = mMovementDirection.create(inputSystem.getEntity());
            movementDir.direction = Direction.EAST;
            if(isWPressed) movementDir.direction = Direction.NORTH_EAST;
            if(isSPressed) movementDir.direction = Direction.SOUTH_EAST;
            isDPressed = true;
        }

    }

    protected void getMovementDirectionOnKeyUP(int keycode) {

        ComponentMapper<MovementDirection> mMovementDirection = inputSystem.getmMovementDirection();
        MovementDirection movementDir = mMovementDirection.get(inputSystem.getEntity());

        if(Input.Keys.W == keycode) {
            if(isAPressed) movementDir.direction = Direction.WEST;
            if(isDPressed) movementDir.direction = Direction.EAST;
            isWPressed = false;
        } else if (Input.Keys.S == keycode) {
            if(isAPressed) movementDir.direction = Direction.WEST;
            if(isDPressed) movementDir.direction = Direction.EAST;
            isSPressed = false;
        }
        if(Input.Keys.A == keycode) {
            if(isWPressed) movementDir.direction = Direction.NORTH;
            if(isSPressed) movementDir.direction = Direction.SOUTH;
            isAPressed = false;
        } else if (Input.Keys.D == keycode) {
            if(isWPressed) movementDir.direction = Direction.NORTH;
            if(isSPressed) movementDir.direction = Direction.SOUTH;
            isDPressed = false;
        }

        if(!isMoving()) {
            mMovementDirection.remove(inputSystem.getEntity());
        }
    }

    protected boolean isMoving() {
        return isWPressed || isDPressed || isSPressed || isAPressed;
    }

    protected void setKeysState(boolean isWPressed, boolean isDPressed, boolean isSPressed, boolean isAPressed) {
        this.isWPressed = isWPressed;
        this.isDPressed = isDPressed;
        this.isSPressed = isSPressed;
        this.isAPressed = isAPressed;
    }
}
