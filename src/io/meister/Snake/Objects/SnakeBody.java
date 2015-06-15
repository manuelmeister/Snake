package io.meister.Snake.Objects;

import io.meister.Snake.Controller.Game;
import io.meister.Snake.Controller.Vector2;
import io.meister.Snake.View.MapObject;

import java.awt.*;

/**
 * The body of the snake
 */
public class SnakeBody extends MapObject{

    /**
     * Color of the snakebody
     */
    private final Color COLOR = new Color(25, 183, 9);

    public SnakeBody(Vector2 pos) {
        this.pos = pos;
        colidable = true;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(COLOR);
        g.fillOval(Game.UNIT * pos.x,Game.UNIT * pos.y, Game.UNIT, Game.UNIT);
    }
}
