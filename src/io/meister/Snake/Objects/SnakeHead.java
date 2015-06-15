package io.meister.Snake.Objects;

import io.meister.Snake.Controller.Game;
import io.meister.Snake.Controller.Vector2;
import io.meister.Snake.View.MapObject;

import java.awt.*;

/**
 * Head of the snake
 */
public class SnakeHead extends MapObject {

    /**
     * Color of the head
     */
    private final Color COLOR = new Color(255,0,0);

    public SnakeHead(Vector2 pos) {
        this.pos = pos;
        colidable = true;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(COLOR);
        g.fillOval(Game.UNIT * pos.x, Game.UNIT * pos.y, Game.UNIT, Game.UNIT);
    }
}
