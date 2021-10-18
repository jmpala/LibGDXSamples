package io.jmpalazzolo.libgdxsamples.Components.movement;

import com.artemis.Component;

public enum Direction {
    NORTH((byte)1),
    NORTH_EAST((byte)2),
    EAST((byte)4),
    SOUTH_EAST((byte)8),
    SOUTH((byte)16),
    SOUTH_WEST((byte)32),
    WEST((byte)64),
    NORTH_WEST((byte)128);

    public byte dir;

    private Direction(byte dir) {
        this.dir = dir;
    }

}
