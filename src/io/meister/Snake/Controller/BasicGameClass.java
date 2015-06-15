package io.meister.Snake.Controller;

import io.meister.Snake.Controller.Events.DiamondTouchedEvent;
import io.meister.Snake.Controller.Events.GameListener;
import io.meister.Snake.Controller.Events.ObstacleTouchedEvent;

import java.util.ArrayList;

public class BasicGameClass {

    protected ArrayList<GameListener> listenerList = new ArrayList<GameListener>();

    public synchronized void addEventListener(GameListener listener){
        listenerList.add(listener);
    }

    public synchronized void removeEventListener(GameListener listener){
        listenerList.remove(listener);
    }

    public synchronized void diamondTouched(Object object){
        DiamondTouchedEvent event = new DiamondTouchedEvent(object);
        for (GameListener listener : listenerList) {
            listener.diamondTouchedEvent(event);
        }
    }

    public synchronized void obstacleTouched(Object object){
        ObstacleTouchedEvent event = new ObstacleTouchedEvent(object);
        for (GameListener listener : listenerList) {
            listener.obstacleTouchedEvent(event);
        }
    }


}
