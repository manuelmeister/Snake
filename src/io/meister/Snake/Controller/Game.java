package io.meister.Snake.Controller;

import io.meister.Snake.Objects.Diamond;
import io.meister.Snake.Objects.Drawable;
import io.meister.Snake.Objects.Snake;
import io.meister.Snake.Objects.SnakeBody;
import io.meister.Snake.View.MapObject;
import io.meister.Snake.View.GUI;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by manuelmeister on 27.04.15.
 */
public class Game implements Drawable {

    private final GUI gui;
    public Snake snake;
    private ArrayList<Diamond> diamonds;

    public static final int UNIT = 10;
    public static final int GAMEFIELD_SIZE_X = 60;
    public static final int GAMEFIELD_SIZE_Y = 40;

    protected MapObject[][] gamefield = new MapObject[GAMEFIELD_SIZE_X][GAMEFIELD_SIZE_Y];

    private final int MIN_DIAMOND_VALUE = 1;
    private final int MAX_DIAMOND_VALUE = 9;

    private final int MIN_DIAMOND_COUNT = 1;
    private final int MAX_DIAMOND_COUNT = 30;

    public Game() {
        this.snake = new Snake(getRandomPosition());
        this.diamonds = new ArrayList<Diamond>();

        int diamondsCount = RandomGenerator.randomInt(MIN_DIAMOND_COUNT, MAX_DIAMOND_COUNT);
        for (int i = 0; i < diamondsCount; i++) {
            this.diamonds.add(
                    new Diamond(RandomGenerator.randomInt(MIN_DIAMOND_VALUE, MAX_DIAMOND_VALUE), getRandomPosition()));
        }

        this.gamefield[snake.head.pos.x][snake.head.pos.y] = this.snake;

        for (Diamond diamond : diamonds) {
            try {
                this.gamefield[diamond.pos.x][diamond.pos.y] = diamond;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Pos x:" + diamond.pos.x + " y:" + diamond.pos.y + " is not inside Array");
            }
        }
        System.out.println(diamondsCount);
        this.gui = new GUI(this);
    }

    private Vector2 getRandomPosition() {
        return getRandomPosition(GAMEFIELD_SIZE_X, GAMEFIELD_SIZE_Y);
    }

    private Vector2 getRandomPosition(int width, int height) {
        return new Vector2(RandomGenerator.randomInt(0, width - 1), RandomGenerator.randomInt(0, height - 1));
    }

    public void draw(Graphics g) {
        for (MapObject[] rows : gamefield) {
            for (MapObject cell : rows) {
                if (cell != null) {
                    cell.draw(g);
                }
            }
        }
    }


    public static void main(String[] args) {
        new Game();
    }
}
