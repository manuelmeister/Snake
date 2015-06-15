package io.meister.Snake.View;

import io.meister.Snake.Controller.Vector2;
import io.meister.Snake.Objects.Drawable;

/**
 * Objectclass used on the Gamefield
 */
public abstract class MapObject implements Drawable {

    /**
     * The state value of death
     */
    public static final int DEAD = 0;
    /**
     * The state value of being alive
     */
    public static final int ALIVE = 1;
    /**
     * The state value of being full
     */
    public static final int FULL = 3;

    /**
     * If this MapObject is collidable
     */
    public boolean colidable = false;

    /**
     * The Position of the object
     */
    public Vector2 pos;
    /**
     * The state of the object
     */
    public int state;
}
