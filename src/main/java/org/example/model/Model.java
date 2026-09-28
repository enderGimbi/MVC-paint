package org.example.model;

import org.example.controller.observer.PanelObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

@Component
public class Model {
    private MyShape currentShape;
    private List<MyShape> list;

    private transient PanelObserver panelObserver;
    @PostConstruct
    public void init() {
        list = new ArrayList<>();
    }

    public void changeShape() {
        panelObserver.updateAll();
    }

    public void draw(Graphics2D g) {
        currentShape.draw(g);
    }

    public void createCurrentShape(MyShape shape) {
        currentShape = shape;
        list.add(currentShape);
        panelObserver.updateAll();
    }

    public void reloadModel(Model model) {
        this.currentShape = model.currentShape;
        this.list = model.list;
        panelObserver.updateAll();
    }
    public List<MyShape> getList() {
        return list;
    }

    @Autowired
    public void setPanelObserver(PanelObserver panelObserver) {
        this.panelObserver = panelObserver;
    }
}
