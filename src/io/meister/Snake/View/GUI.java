package io.meister.Snake.View;

import io.meister.Snake.Controller.Game;
import io.meister.Snake.Controller.Vector2;
import io.meister.Snake.Objects.Snake;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


/**
 * Die Klasse GUI stellt das Fenster (JFrame) und
 * das Spielfeld (JPanel) des Snake-Spiels zur Verfügung.
 *
 * @author A. Scheidegger, M.Frieden
 * @version 4.0
 */

@SuppressWarnings("serial")
public class GUI extends JFrame implements KeyListener {

    /**
     * Referenz auf das io.meister.Snake.Controller.Game-Objekt.
     */
    private Game game;

    /**
     * Das JPanel, welches als Spielfeld dient. Anonyme innere Klasse.
     */
    private JPanel spielfeld = new JPanel() {
        /**
         * Wird automatisch aufgerufen, wenn das Fenster neu gezeichnet werden
         * muss. Delegiert das Zeichnen des Spiels an das io.meister.Snake.Controller.Game-Objekt.
         * @param g Graphics-Objekt zum Zeichnen
         */
        @Override
        public void paintComponent(Graphics g) {
            game.draw(g);
        }
    };

    /**
     * Konstruktor. Initialisiert den Frame. Registriert das io.meister.Snake.Controller.Game-Objekt
     * als KeyListener.
     */
    public GUI(Game game) {
        this.game = game;
        //TODO 12 is Bar on top
        setSize(Game.GAMEFIELD_SIZE_X * Game.UNIT, 12 + Game.GAMEFIELD_SIZE_Y * Game.UNIT);
        setTitle("Snake V4.0");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(spielfeld);
        setVisible(true);
        addKeyListener(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.loop();
    }

    /**
     * Gibt die Breite des Spielfeldes zurück.
     *
     * @return Breite des Spielfeldes.
     */
    public int getBreite() {
        return spielfeld.getWidth();
    }

    /**
     * Gibt die Höhe des Spielfeldes zurück.
     *
     * @return Höhe des Spielfeldes
     */
    public int getHoehe() {
        return spielfeld.getHeight();
    }


    public void loop() {
        while (this.game.snake.state == Snake.ALIVE) {
            try {
                this.game.snake.move();
                repaint();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.game.snake.dir = new Vector2(0,-1);
                break;
            case KeyEvent.VK_DOWN:
                this.game.snake.dir = new Vector2(0,1);
                break;
            case KeyEvent.VK_LEFT:
                this.game.snake.dir = new Vector2(-1,0);
                break;
            case KeyEvent.VK_RIGHT:
                this.game.snake.dir = new Vector2(1,0);
                break;
            default:
                System.out.println("Not accepted");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
