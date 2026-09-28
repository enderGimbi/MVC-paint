package org.example.controller.observer;

import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Controller
public class PanelObserver {
    private Set<PanelSubscriber> observers;

    @PostConstruct
    public void init() {
        observers = new HashSet<>();
    }

    public void addSubscriber(PanelSubscriber subscriber) {
        observers.add(subscriber);
    }

    public void removeObserver(PanelSubscriber subscriber) {
        observers.remove(subscriber);
    }

    public void updateAll() {
        observers.forEach(PanelSubscriber::update);
    }
}
