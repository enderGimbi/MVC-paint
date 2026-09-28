package org.example.controller;

import org.example.controller.action.ActionDraw;
import org.example.controller.menu.MenuController;
import org.example.controller.menu.MenuObserver;
import org.example.controller.menu.Subscriber;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.shape.factory.ShapeType;
import org.example.model.shape.fill.FillBehavior;
import org.example.view.MyFrame;
import org.example.view.MyPanel;
import org.example.controller.observer.PanelObserver;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.awt.*;

@org.springframework.stereotype.Controller
public class Controller implements Subscriber {
    private Model model;
    private MyFrame frame;
    private MyPanel panel;
    private MyShape shape;
    private ActionDraw actionDraw;
    private MenuController menuController;

    private MenuObserver menuObserver;

    private PanelObserver panelObserver;

    @PostConstruct
    public void init() {
        frame.setPanel(panel);
        frame.setJMenuBar(menuController.createMenuBar());
        frame.revalidate();
        shape = createShapeWithParameters();
        actionDraw.setSampleShape(shape);
        menuObserver.addSubscriber(this);
        panelObserver.addSubscriber(panel);
    }

    public void mousePressed(Point point) {
        actionDraw.createShape(point);
    }

    public void mouseDragget(Point point) {
        actionDraw.stretchShape(point);
    }

    public void repaintAllModels(Graphics2D graphics) {
        model.getList().forEach(myShape -> myShape.draw(graphics));
    }

    public MyShape createShapeWithParameters() {
        Color color = menuController.getSelectedColor();
        FillBehavior fillBehavior = menuController.getSelectedFill();
        ShapeType shapeType = menuController.getSelectedShape();
        return shapeType.createMyShape(color, fillBehavior);
    }

    @Override
    public void notifyUpdate() {
        this.shape = createShapeWithParameters();
        actionDraw.setSampleShape(shape);
        model.changeShape();
    }

    @Autowired
    public void setModel(Model model) {
        this.model = model;
    }
    @Autowired
    public void setFrame(MyFrame frame) {
        this.frame = frame;
    }
    @Autowired
    public void setPanel(MyPanel panel) {
        this.panel = panel;
    }
    @Autowired
    public void setActionDraw(ActionDraw actionDraw) {
        this.actionDraw = actionDraw;
    }
    @Autowired
    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }
    @Autowired
    public void setMenuObserver(MenuObserver menuObserver) {
        this.menuObserver = menuObserver;
    }
    @Autowired
    public void setPanelObserver(PanelObserver panelObserver) {
        this.panelObserver = panelObserver;
    }
}
