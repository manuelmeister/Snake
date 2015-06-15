package io.meister.Snake.Objects;

import io.meister.Snake.Controller.Game;
import io.meister.Snake.Controller.Vector2;
import io.meister.Snake.View.MapObject;

import java.awt.*;
import java.util.ArrayList;

/**
 * The visible snake on the gamefield
 */
public class Snake extends MapObject{

    /**
     * Direction the snake is going
     */
    public Vector2 dir = new Vector2(1,0);

    /**
     * Head of the snake
     */
    public SnakeHead head;
    /**
     * Body tiles of the snake
     */
    public ArrayList<SnakeBody> body = new ArrayList<SnakeBody>();

    /**
     * Length (count of body tiles)
     */
    private final int startLength = 1;
    private boolean movable = false;

    public Snake(Vector2 pos) {
        this.pos = pos;
        this.head = new SnakeHead(this.pos);
        for (int i = 1; i <= startLength; i++) {
            this.body.add(new SnakeBody(new Vector2(pos.x - dir.x * i, pos.y - dir.y * i)));
        }
        this.state = ALIVE;
    }

    @Override
    public void draw(Graphics g) {
        for (SnakeBody snakeBody : body) {
            snakeBody.draw(g);
        }
        head.draw(g);
    }

    /**
     * Move all tiles, including head and body
     */
    public void move(){
        for (int i = body.size()-1; i > 0; i--) {
            body.get(i).pos = body.get(i-1).pos.get();
        }
        body.get(0).pos = this.pos.get();
        this.pos.add(dir);
    }

    /**
     * Move and add a tile at the end of the body
     */
    public void eatMove(){
        body.add(new SnakeBody(body.get(body.size()-1).pos));
        for (int i = body.size()-1; i > 0; i--) {
            System.out.println(body.get(i).pos);
            body.get(i).pos = body.get(i-1).pos.get();
        }
        body.get(0).pos = this.pos.get();
        this.pos.add(dir);
    }
}
