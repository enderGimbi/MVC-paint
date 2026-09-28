package org.example.view;

import java.util.Vector;

public class Observable {
    private boolean changed = false;
    private final Vector<Observer> subscribers;

    public Observable() {
        subscribers = new Vector<>();
    }

    public synchronized void subscribe(Observer observer){
        if(observer==null){
            throw new NullPointerException();
        }
        if(!subscribers.contains(observer)){
            subscribers.add(observer);
        }
    }

    public synchronized void unsubscribe(Observer observer){
        if(observer==null){
            throw new NullPointerException();
        }
        subscribers.remove(observer);
    }


    public void setChanged() {
        changed=true;
    }

    public void clearChanged(){
        changed=false;
    }

    public void notifyObservers() {
        Object[] arrLocal;
        synchronized (this){
            if(!changed)
                return;
            arrLocal = subscribers.toArray();
            clearChanged();
        }
        for (int i = arrLocal.length-1; i >=0 ; i--) {
            ((Observer)arrLocal[i]).update(this,null);
        }
    }
}
