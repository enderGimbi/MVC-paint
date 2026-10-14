package org.example.controller;

import org.example.controller.action.ActionDraw;
import org.example.controller.action.Action;
import org.example.controller.action.ActionMove;
import org.example.controller.state.UndoMachine;
import org.example.model.Model;
import org.example.model.fill.Fill;
import org.example.model.factory.ShapeType;
import org.example.view.MyFrame;
import org.example.view.MyPanel;

import javax.swing.*;
import java.awt.*;

public class Controller {
    private static Controller instance;
    private final Model model;
    private final MyFrame frame;
    private final MyPanel panel;
    private Action currentAction;
    private UndoMachine history;

    private Controller() {
        model = new Model();
        model.setShape(ShapeType.Rectangle);
        model.setColor(Color.BLACK);
        model.setFb(new Fill());
        model.setMyShape();

        currentAction = new ActionDraw(model);


        history = new UndoMachine();

        panel = new MyPanel(this);

        model.subscribe(panel);

        frame = new MyFrame();
        frame.setPanel(panel);
    }

    public void mousePressed(Point p) {
        currentAction.mousePressed(p);
    }

    public void mouseDragged(Point p) {
        currentAction.mouseDragged(p);
        model.setChanged();
        model.notifyObservers();
    }

    public void mouseReleased(){
        history.add(currentAction);
        if (currentAction instanceof ActionDraw) {
            currentAction = new ActionDraw(model);
        } else if (currentAction instanceof ActionMove) {
            currentAction = new ActionMove(model);
        }
        model.setChanged();
        model.notifyObservers();
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }

    public static void getInstance(){
        if(instance==null) {
            instance = new Controller();
        }
    }

    public Model getModel() {
        return model;
    }

    public void setCurrentAction(Action currentAction) {
        this.currentAction = currentAction;
    }

    public UndoMachine getHistory() {
        return history;
    }
}
