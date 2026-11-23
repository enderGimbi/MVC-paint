package org.example.controller.action;

import org.example.model.Model;
import org.example.model.fill.FillBehavior;

public class ActionChangeFill extends Action{
    private final Model model;
    private final FillBehavior newFillBehavior;
    private FillBehavior oldFillBehavior;

    public ActionChangeFill(Model model, FillBehavior newFillBehavior) {
        this.model = model;
        this.newFillBehavior = newFillBehavior;
    }

    @Override
    public void execute() {
        oldFillBehavior = model.getFb();
        model.changeFb(newFillBehavior);
    }

    @Override
    public void unexecute() {
        model.changeFb(oldFillBehavior);
    }
}
