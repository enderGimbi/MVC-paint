package org.example.controller;

import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.fill.FillBehavior;
import org.example.model.fill.NoFill;
import org.example.view.MyFrame;
import org.example.view.MyPanel;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

// TODO: Сделать singleton класс
public class Controller {
    private final Model model;
    private final MyFrame frame;
    private final MyPanel panel;
    private Point2D firstPoint;
    private Point2D secondPoint;
    public Controller() {
        model = new Model();

        FillBehavior fillBehavior = new NoFill();
        RectangularShape rectangularShape = new Rectangle2D.Double();
        fillBehavior.setShape(rectangularShape);
        MyShape shape = MyShape.builder()
                .shape(rectangularShape)
                .fb(fillBehavior)
                .color(Color.BLUE)
                .build();
        fillBehavior.setShape(rectangularShape);

        model.setCurrentShape(shape);
        panel = new MyPanel(this);
        // TODO: Поменять наблюдатель на более современную реализацию
        model.addObserver(panel);

        frame = new MyFrame();
        frame.setPanel(panel);
    }
    public void getPointOne(Point2D p){
        firstPoint = p;
    }
    public void getPointTwo(Point2D p){
        secondPoint = p;
        model.changeShape(firstPoint, secondPoint);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }
}
