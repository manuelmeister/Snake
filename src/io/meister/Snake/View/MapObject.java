package io.meister.Snake.View;

import io.meister.Snake.Controller.Vector2;
import io.meister.Snake.Objects.Drawable;

import java.awt.*;

/**
 * Created by manuelmeister on 27.04.15.
 */
public abstract class MapObject implements Drawable {

    public static final int DEAD = 0;
    public static final int ALIVE = 1;
    public Vector2 pos;
    public int state;
}
