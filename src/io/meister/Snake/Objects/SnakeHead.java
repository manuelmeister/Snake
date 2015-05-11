package io.meister.Snake.Objects;

import io.meister.Snake.Controller.Game;
import io.meister.Snake.Controller.Vector2;
import io.meister.Snake.View.MapObject;

import java.awt.*;

/**
 * Created by manuelmeister on 11.05.15.
 */
public class SnakeHead extends MapObject {

    private final Color COLOR = new Color(255,0,0);

    public SnakeHead(Vector2 pos) {
        this.pos = pos;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(COLOR);
        g.fillOval(Game.UNIT * pos.x, Game.UNIT * pos.y, Game.UNIT, Game.UNIT);
    }
}
