package io.meister.Snake.Controller.Events;

public class DiamondTouchedEvent extends BasicEventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public DiamondTouchedEvent(Object source) {
        super(source);
    }
}
