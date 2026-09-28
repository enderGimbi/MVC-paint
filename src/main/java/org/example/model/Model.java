package org.example.model;

import lombok.Setter;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.Observable;

public class Model extends Observable {
    @Setter
    private MyShape currentShape;

    public void changeShape(Point2D x, Point2D y) {
        currentShape.setFrame(x, y);
        this.setChanged();
        this.notifyObservers();
    }

    public void draw(Graphics2D g) {
        currentShape.draw(g);
    }
}
