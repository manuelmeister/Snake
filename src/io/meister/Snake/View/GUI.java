package io.meister.Snake.View;

import io.meister.Snake.Controller.Game;

import java.awt.Graphics;

import javax.swing.*;


/**
 * Die Klasse GUI stellt das Fenster (JFrame) und
 * das Spielfeld (JPanel) des Snake-Spiels zur Verfuegung.
 *
 * @author A. Scheidegger, M.Frieden
 * @version 4.0
 */

@SuppressWarnings("serial")
public class GUI extends JFrame{

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
     * @param game Game
     */
    public GUI(Game game) {
        this.game = game;
        //TODO 22 is Bar on top
        setSize(Game.GAMEFIELD_SIZE_X * Game.UNIT, 22 + Game.GAMEFIELD_SIZE_Y * Game.UNIT);
        setTitle("Snake V4.0");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(spielfeld);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Gibt die Breite des Spielfeldes zurueck.
     *
     * @return Breite des Spielfeldes.
     */
    public int getBreite() {
        return spielfeld.getWidth();
    }

    /**
     * Gibt die Hoehe des Spielfeldes zurueck.
     *
     * @return Hoehe des Spielfeldes
     */
    public int getHoehe() {
        return spielfeld.getHeight();
    }

}
