package org.example.controller;

import lombok.Getter;
import lombok.Setter;
import org.example.controller.action.ActionDraw;
import org.example.controller.action.Action;
import org.example.controller.action.ActionMove;
import org.example.controller.state.UndoMachine;
import org.example.model.Model;
import org.example.model.fill.Fill;
import org.example.model.factory.ShapeType;
import org.example.utils.SaveToFileUtil;
import org.example.view.MyFrame;
import org.example.view.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Controller {
    private static Controller instance;
    @Getter
    private final Model model;
    private final MyFrame frame;
    private final MyPanel panel;
    @Setter
    private Action currentAction;
    @Getter
    private UndoMachine history;
    @Getter
    private SaveToFileUtil fileUtil;

    private Controller() {
        model = new Model();
        model.setShape(ShapeType.Rectangle);
        model.setColor(Color.BLACK);
        model.setFb(new Fill());
        model.setMyShape();

        currentAction = new ActionDraw(model);

        history = new UndoMachine();

        try {
            fileUtil = new SaveToFileUtil(model);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при инициализации файла shapes.json");
        }

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
        model.notifyObservers();
    }

    public void mouseReleased() {
        history.add(currentAction);
        if (currentAction instanceof ActionDraw) {
            currentAction = new ActionDraw(model);
        } else if (currentAction instanceof ActionMove) {
            currentAction = new ActionMove(model);
        }
        model.notifyObservers();
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }

    public static void getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
    }

}
