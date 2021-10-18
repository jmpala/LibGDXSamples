package io.jmpalazzolo.libgdxsamples.systems.input.states;

import com.badlogic.gdx.InputProcessor;

public abstract class InputState implements InputProcessor {

    protected boolean isWPressed;
    protected boolean isDPressed;
    protected boolean isSPressed;
    protected boolean isAPressed;

    public InputState() {

    }

    abstract void stand();

    abstract void walk();

    abstract void meditate();

}
