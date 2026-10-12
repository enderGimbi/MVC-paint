package org.example.controller;

import org.example.controller.action.ActionDraw;
import org.example.controller.action.ActionMove;
import org.example.controller.action.Actions;
import org.example.controller.action.ActionsHistory;
import org.example.model.Model;
import org.example.model.fill.Fill;
import org.example.model.factory.ShapeType;
import org.example.view.MyFrame;
import org.example.view.MyPanel;

import java.awt.*;

public class Controller {
    private static Controller instance;
    private final Model model;
    private final MyFrame frame;
    private final MyPanel panel;
    private final ActionDraw actionDraw;
    private final ActionMove actionMove;
    private Actions currentAction;
    private ActionsHistory history;

    private Controller() {
        model = new Model();
        model.setShape(ShapeType.Rectangle);
        model.setColor(Color.BLACK);
        model.setFb(new Fill());
        model.setMyShape();

        currentAction = Actions.DRAW;
        actionDraw = new ActionDraw(model);
        actionDraw.setSampleShape(model.getCurrentShape());
        actionMove = new ActionMove(model);

        history = new ActionsHistory();

        panel = new MyPanel(this);

        model.subscribe(panel);

        frame = new MyFrame();
        frame.setPanel(panel);
    }

    public void mousePressed(Point p) {
        switch (currentAction){
            case DRAW -> {
                model.setMyShape();
                actionDraw.setSampleShape(model.getCurrentShape());
                actionDraw.createShape(p);

            }
            case MOVE ->{
                actionMove.chooseShape(p);

            }
        }
    }

    public void mouseDragged(Point p) {
        switch (currentAction){
            case DRAW -> {
                actionDraw.stretchShape(p);

            }
            case MOVE -> {
                actionMove.moveShape(p);

            }
        }
        model.setChanged();
        model.notifyObservers();
    }

    public void mouseReleased(){
        history.saveState(model.getShapesList());
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

    public void setCurrentAction(Actions currentAction) {
        this.currentAction = currentAction;
    }

    public ActionsHistory getHistory() {
        return history;
    }
}
