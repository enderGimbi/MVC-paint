package org.example.controller;

import lombok.Getter;
import lombok.Setter;
import org.example.controller.action.ActionDraw;
import org.example.controller.action.Action;
import org.example.controller.action.ActionsHistory;
import org.example.model.Model;
import org.example.model.fill.Fill;
import org.example.model.factory.ShapeType;
import org.example.utils.SaveToFileUtil;
import org.example.view.MyFrame;
import org.example.view.MyPanel;

import javax.swing.*;
import java.awt.*;

public class Controller {
    private static Controller instance;
    @Getter
    private final Model model;
    private final MyFrame frame;
    private final MyPanel panel;
    @Setter
    private Action currentAction;
    @Getter
    private ActionsHistory history;
    @Getter
    private SaveToFileUtil fileUtil;

    private Controller() {
        model = new Model();
        model.setShape(ShapeType.Rectangle);
        model.setColor(Color.BLACK);
        model.setFb(new Fill());
        model.setMyShape();

        currentAction = new ActionDraw(model);
        currentAction.update();

        history = new ActionsHistory();

        fileUtil = new SaveToFileUtil(model);

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

}
