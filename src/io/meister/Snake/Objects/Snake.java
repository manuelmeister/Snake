package io.meister.Snake.Objects;

import io.meister.Snake.Controller.Game;
import io.meister.Snake.Controller.Vector2;
import io.meister.Snake.View.MapObject;

import java.awt.*;
import java.util.ArrayList;

public class Snake extends MapObject{

    public Vector2 pos;
    public Vector2 dir = new Vector2(1,0);

    public SnakeHead head;
    public ArrayList<SnakeBody> body = new ArrayList<SnakeBody>();

    private final int startLength = 4;
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
        head.draw(g);
        for (SnakeBody snakeBody : body) {
            snakeBody.draw(g);
        }
    }

    public void move(){
        for (int i = body.size()-1; i > 0; i--) {
            System.out.println(body.get(i));
            body.get(i).pos = body.get(i-1).pos.get();
        }
        body.get(0).pos = this.pos.get();
        this.pos.add(dir);
    }
}
