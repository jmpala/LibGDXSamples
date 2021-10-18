package io.jmpalazzolo.libgdxsamples.systems.input.states;

import com.artemis.ComponentMapper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.jmpalazzolo.libgdxsamples.Components.movement.Direction;
import io.jmpalazzolo.libgdxsamples.Components.movement.MovementDirection;
import io.jmpalazzolo.libgdxsamples.systems.input.InputSystem;
import org.pmw.tinylog.Logger;

public class WalkingState extends InputState {

    private static WalkingState INSTANCE;
    private InputSystem inputSystem;

    private WalkingState(InputSystem inputSystem) {
        this.inputSystem = inputSystem;
        isWPressed = false;
        isDPressed = false;
        isSPressed = false;
        isAPressed = false;
    }

    public static InputState getINSTANCE(InputSystem inputSystem) {
        if (INSTANCE == null) {
            INSTANCE = new WalkingState(inputSystem);
        }
        Logger.info("State: Walking");
        Gdx.input.setInputProcessor(INSTANCE);
        return INSTANCE;
    }

    @Override
    public void stand() {
        InputState inputState = StandingState.getINSTANCE(inputSystem);
        inputState.isWPressed = isWPressed;
        inputState.isDPressed = isDPressed;
        inputState.isSPressed = isSPressed;
        inputState.isAPressed = isAPressed;
        inputSystem.setCurrentState(inputState);
    }

    @Override
    public void walk() {
        // Unimplemented
    }

    @Override
    public void meditate() {
        // Unimplemented
    }

    @Override
    public boolean keyDown(int keycode) {

        int entity = inputSystem.getEntity();
        ComponentMapper<MovementDirection> mMovementDirection = inputSystem.getmMovementDirection();
        MovementDirection movementDir;

        if(Input.Keys.W == keycode) {
            movementDir = mMovementDirection.create(entity);
            movementDir.direction = Direction.NORTH;
            if(isAPressed) movementDir.direction = Direction.NORTH_WEST;
            if(isDPressed) movementDir.direction = Direction.NORTH_EAST;
            isWPressed = true;
        } else if (Input.Keys.S == keycode) {
            movementDir = mMovementDirection.create(entity);
            movementDir.direction = Direction.SOUTH;
            if(isAPressed) movementDir.direction = Direction.SOUTH_WEST;
            if(isDPressed) movementDir.direction = Direction.SOUTH_EAST;
            isSPressed = true;
        }
        if(Input.Keys.A == keycode) {
            movementDir = mMovementDirection.create(entity);
            movementDir.direction = Direction.WEST;
            if(isWPressed) movementDir.direction = Direction.NORTH_WEST;
            if(isSPressed) movementDir.direction = Direction.SOUTH_WEST;
            isAPressed = true;
        } else if (Input.Keys.D == keycode) {
            movementDir = mMovementDirection.create(entity);
            movementDir.direction = Direction.EAST;
            if(isWPressed) movementDir.direction = Direction.NORTH_EAST;
            if(isSPressed) movementDir.direction = Direction.SOUTH_EAST;
            isDPressed = true;
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {

        int entity = inputSystem.getEntity();
        ComponentMapper<MovementDirection> mMovementDirection = inputSystem.getmMovementDirection();
        MovementDirection movementDir;

        if(Input.Keys.W == keycode) {
            movementDir = mMovementDirection.get(entity);
            if(isAPressed) movementDir.direction = Direction.WEST;
            if(isDPressed) movementDir.direction = Direction.EAST;
            isWPressed = false;
        } else if (Input.Keys.S == keycode) {
            movementDir = mMovementDirection.get(entity);
            if(isAPressed) movementDir.direction = Direction.WEST;
            if(isDPressed) movementDir.direction = Direction.EAST;
            isSPressed = false;
        }
        if(Input.Keys.A == keycode) {
            movementDir = mMovementDirection.get(entity);
            if(isWPressed) movementDir.direction = Direction.NORTH;
            if(isSPressed) movementDir.direction = Direction.SOUTH;
            isAPressed = false;
        } else if (Input.Keys.D == keycode) {
            movementDir = mMovementDirection.get(entity);
            if(isWPressed) movementDir.direction = Direction.NORTH;
            if(isSPressed) movementDir.direction = Direction.SOUTH;
            isDPressed = false;
        }

        boolean isMoving = isWPressed || isDPressed || isSPressed || isAPressed;

        if (!isMoving) {
            mMovementDirection.remove(entity);
            stand();
        }
        return true;
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
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
