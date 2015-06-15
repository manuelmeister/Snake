package io.meister.Snake.Controller.Events;

import java.util.EventListener;

public interface GameListener extends EventListener {
    public void diamondTouchedEvent(DiamondTouchedEvent event);
    public void obstacleTouchedEvent(ObstacleTouchedEvent event);
}
