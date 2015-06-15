package io.meister.Snake.Objects;

import io.meister.Snake.Controller.Game;
import io.meister.Snake.Controller.Vector2;
import io.meister.Snake.View.MapObject;

import java.awt.*;

/**
 * Diamond on the Gamefield
 */
public class Diamond extends MapObject {

    public int index;
    public final int value;
    public final Vector2 pos;
    private final Color COLOR = new Color(35, 137, 252);

    public Diamond(int index, int value,Vector2 pos) {
        this.index = index;
        this.value = value;
        this.pos = pos;
        colidable = true;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(COLOR);
        g.fillRect(Game.UNIT * pos.x, Game.UNIT * pos.y, Game.UNIT, Game.UNIT);
    }
}
