package io.meister.Snake.Controller.Events;

public class ObstacleTouchedEvent extends BasicEventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ObstacleTouchedEvent(Object source) {
        super(source);
    }
}
