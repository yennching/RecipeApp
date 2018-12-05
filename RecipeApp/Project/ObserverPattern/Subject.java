package ObserverPattern;


import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private List<Observer> observers;

    public Subject(){
        observers = new ArrayList<>();
    }

    public List<Observer> getObservers() {
        return observers;
    }

    //MODIFIES: this
    //EFFECTS: adds observer to list of observers
    public void addObserver(Observer o) {
        if(!observers.contains(o)) {
            observers.add(o);
        }
    }

    public void removeObserver(Observer o) {
        if(!observers.contains(o)) {
            observers.remove(o);
        }
    }

    public void notifyObservers(RecipeConsumers rc){
        for(Observer o : observers){
            o.update(rc);
        }
    }


}
