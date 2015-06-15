package io.meister.Snake.Controller;

import io.meister.Snake.Controller.Events.DiamondTouchedEvent;
import io.meister.Snake.Controller.Events.GameListener;
import io.meister.Snake.Controller.Events.ObstacleTouchedEvent;
import io.meister.Snake.Controller.Exceptions.GameFinishedException;
import io.meister.Snake.Objects.*;
import io.meister.Snake.View.MapObject;
import io.meister.Snake.View.GUI;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Logical part of the game
 */
public class Game extends BasicGameClass implements Drawable, KeyListener, GameListener {

    private boolean win = false;
    private final GUI gui;
    public Snake snake;
    private ArrayList<Diamond> diamonds;
    private int diamondsCount;

    public static final int UNIT = 10;
    public static final int GAMEFIELD_SIZE_X = 60;
    public static final int GAMEFIELD_SIZE_Y = 40;

    private static final Vector2 UP = new Vector2(0, -1);
    private static final Vector2 DOWN = new Vector2(0, 1);
    private static final Vector2 LEFT = new Vector2(-1, 0);
    private static final Vector2 RIGHT = new Vector2(1, 0);

    protected MapObject[][] gamefield = new MapObject[GAMEFIELD_SIZE_X][GAMEFIELD_SIZE_Y];

    private final int MIN_DIAMOND_VALUE = 1;
    private final int MAX_DIAMOND_VALUE = 9;

    private final int MIN_DIAMOND_COUNT = 1;
    private final int MAX_DIAMOND_COUNT = 10;

    /**
     * Initialize game
     */
    public Game() {

        this.snake = new Snake(getRandomPosition());
        this.diamonds = new ArrayList<Diamond>();

        diamondsCount = RandomGenerator.randomInt(MIN_DIAMOND_COUNT, MAX_DIAMOND_COUNT);
        for (int i = 0; i < diamondsCount; i++) {
            this.diamonds.add(
                    new Diamond(i, RandomGenerator.randomInt(MIN_DIAMOND_VALUE, MAX_DIAMOND_VALUE), getRandomPosition()));
        }

        for (Diamond diamond : diamonds) {
            try {
                this.gamefield[diamond.pos.x][diamond.pos.y] = diamond;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Pos x:" + diamond.pos.x + " y:" + diamond.pos.y + " is not inside Array");
            }
        }
        System.out.println(diamondsCount);
        this.gui = new GUI(this);
        this.gui.addKeyListener(this);
        addEventListener(this);
        loop();
    }

    /**
     * @return Vector2 a random position inside Gamefield
     */
    private Vector2 getRandomPosition() {
        return getRandomPosition(GAMEFIELD_SIZE_X, GAMEFIELD_SIZE_Y);
    }

    /**
     * Gets a random position inside this range
     *
     * @param width  int
     * @param height int
     * @return Vector2
     */
    private Vector2 getRandomPosition(int width, int height) {
        return new Vector2(RandomGenerator.randomInt(0, width - 1), RandomGenerator.randomInt(0, height - 1));
    }

    /**
     * Calls draw on all MapObjects
     *
     * @param g Graphics
     */
    public void draw(Graphics g) {
        for (MapObject[] rows : gamefield) {
            for (MapObject cell : rows) {
                if (cell != null) {
                    cell.draw(g);
                }
            }
        }
    }

    /**
     * Loops until the snake isn't alive anymore
     */
    public void loop() {
        while (snake.state == Snake.ALIVE) {
            try {
                isElementColidable(snake.dir);
                for (SnakeBody snakeBody : snake.body) {
                    gamefield[snakeBody.pos.x][snakeBody.pos.y] = null;
                }
                snake.move();
                update();
                this.gui.repaint();
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            } catch (GameFinishedException e) {
                break;
            }
        }
        String msg = (win) ? "Win" : "Gameover";
        this.gui.setTitle(msg + " Punkte:" + snake.body.size());

    }

    /**
     * Gets the input keys
     *
     * @param key int
     */
    public void changeDirectionOfTheSnake(int key) {
        switch (key) {
            case KeyEvent.VK_UP:
                this.snake.dir = UP;
                break;
            case KeyEvent.VK_DOWN:
                this.snake.dir = DOWN;
                break;
            case KeyEvent.VK_LEFT:
                this.snake.dir = LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                this.snake.dir = RIGHT;
                break;
            default:
                System.out.println("Not accepted");
        }
    }

    /**
     * Is the element in the direction colidable with the snake
     *
     * @param direction Vector2
     */
    public void isElementColidable(Vector2 direction) throws GameFinishedException {
        try {
            if (gamefield[snake.pos.x + direction.x][snake.pos.y + direction.y].colidable) {
                if (gamefield[snake.pos.x + direction.x][snake.pos.y + direction.y] instanceof Diamond) {
                    diamondTouched((Diamond) gamefield[snake.pos.x + direction.x][snake.pos.y + direction.y]);
                } else {
                    obstacleTouched(gamefield[snake.pos.x + direction.x][snake.pos.y + direction.y]);
                    throw new GameFinishedException();
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            obstacleTouched(new Wall());
        } catch (NullPointerException e) {
            //Normal field
        }
    }

    public static void main(String[] args) {
        new Game();
    }

    public void update() throws GameFinishedException {
        try{
            gamefield[snake.head.pos.x][snake.head.pos.y] = this.snake.head;
            for (SnakeBody snakeBody : snake.body) {
                gamefield[snakeBody.pos.x][snakeBody.pos.y] = snakeBody;
            }
        }catch (Exception e){
            throw new GameFinishedException();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * If a key is pressed, changeDirectionOfTheSnake gets called
     *
     * @param e KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        changeDirectionOfTheSnake(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void diamondTouchedEvent(DiamondTouchedEvent event) {
        gamefield[snake.pos.x + snake.dir.x][snake.pos.y + snake.dir.y] = null;
        if (--diamondsCount <= 0) {
            snake.state = Snake.FULL;
            win = true;
        } else {
            snake.eatMove();
        }
    }

    @Override
    public void obstacleTouchedEvent(ObstacleTouchedEvent event) {
        if(event.getSource() instanceof Wall){
            System.out.println("Wall touched");
        }else {
            System.out.println("Snake touched");
        }
        System.out.println(snake.pos);
        snake.state = Snake.DEAD;
    }
}
