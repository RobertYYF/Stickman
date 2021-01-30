package stickman.Memento;

import java.util.Map;

public class Originator {

    private Map<String, Object> state;

    public void setState(Map<String, Object> state) {
        this.state = state;
    }

    public Map<String, Object> getState() {
        return state;
    }

    public Memento saveStateToMemento() {
        return new Memento(state);
    }

    public void getStateFromMemento(Memento memento) {
        state = memento.getState();
    }
}
