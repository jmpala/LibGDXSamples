package io.jmpalazzolo.libgdxsamples.systems.input.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.jmpalazzolo.libgdxsamples.systems.input.InputSystem;
import org.pmw.tinylog.Logger;

/**
 * InputState: "Standing"
 *
 * Implements only:
 * - walk()
 * - meditate()
 */
public class StandingState extends InputState {

    private static StandingState INSTANCE;

    private StandingState(InputSystem inputSystem) {
        super(inputSystem);
    }

    public static InputState getINSTANCE(InputSystem inputSystem) {
        if (INSTANCE == null) {
            INSTANCE = new StandingState(inputSystem);
        }
        Logger.info("State: Standing");
        Gdx.input.setInputProcessor(INSTANCE);
        return INSTANCE;
    }

    @Override
    public void stand() {
        // Unimplemented
    }

    @Override
    public void walk() {
        InputState inputState = WalkingState.getINSTANCE(inputSystem);
        inputState.setKeysState(isWPressed, isDPressed, isSPressed, isAPressed);
        inputSystem.setCurrentState(inputState);
    }

    @Override
    public void meditate() {
        InputState inputState  = MeditatingState.getINSTANCE(inputSystem);
        inputState.setKeysState(isWPressed, isDPressed, isSPressed, isAPressed);
        inputSystem.setCurrentState(inputState);
    }


    @Override
    public boolean keyDown(int keycode) {

        getMovementDirectionOnKeyDown(keycode);

        if(isMoving()) {
            walk();
        } else if (Input.Keys.M == keycode) {
            meditate();
        }

        return true;
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
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
