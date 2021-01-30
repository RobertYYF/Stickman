package stickman.Memento;

import java.util.Map;

public class Memento {

    private Map<String, Object> state;

    public Memento(Map<String, Object> state) {
        this.state = state;
    }

    public Map<String, Object> getState() {
        return state;
    }

}