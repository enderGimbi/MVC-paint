package org.example.controller.menu;

import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Controller
public class MenuObserver {
    private Set<Subscriber> subscribers;

    @PostConstruct
    public void init() {
        subscribers = new HashSet<>();
    }

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void notifyAllSubscribers() {
        for (Subscriber subscriber : subscribers) {
            subscriber.notifyUpdate();
        }
    }
}
