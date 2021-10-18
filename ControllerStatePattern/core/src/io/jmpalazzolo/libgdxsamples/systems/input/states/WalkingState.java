package io.jmpalazzolo.libgdxsamples.systems.input.states;

import com.badlogic.gdx.Gdx;
import io.jmpalazzolo.libgdxsamples.systems.input.InputSystem;
import org.pmw.tinylog.Logger;

/**
 * InputState: "Walking"
 *
 * Implements only:
 * - stand()
 *
 */
public class WalkingState extends InputState {

    private static WalkingState INSTANCE;

    private WalkingState(InputSystem inputSystem) {
        super(inputSystem);
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
        inputState.setKeysState(isWPressed, isDPressed, isSPressed, isAPressed);
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

        getMovementDirectionOnKeyDown(keycode);

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {

        getMovementDirectionOnKeyUP(keycode);

        if (!isMoving()) {
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
