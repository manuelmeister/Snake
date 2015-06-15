package io.meister.Snake.Controller;

/**
 * Handy point replacement
 */
public class Vector2 {
    public int x;
    public int y;

    public Vector2() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector2 vector2){
        this.x += vector2.x;
        this.y += vector2.y;
    }

    public void subtract(Vector2 vector2){
        this.x -= vector2.x;
        this.y -= vector2.y;
    }

    public void multiply(Vector2 vector2){
        this.x *= vector2.x;
        this.y *= vector2.y;
    }

    public void divide(Vector2 vector2){
        this.x /= vector2.x;
        this.y /= vector2.y;
    }

    public Vector2 get(){
        return new Vector2(this.x,this.y);
    }

    @Override
    public String toString() {
        return "x=" + x +", y=" + y ;
    }
}
