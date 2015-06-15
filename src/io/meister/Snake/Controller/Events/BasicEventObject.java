package io.meister.Snake.Controller.Events;

import java.util.EventObject;

public class BasicEventObject extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public BasicEventObject(Object source) {
        super(source);
    }

    public Object getSource(){
        return this.source;
    }
}
