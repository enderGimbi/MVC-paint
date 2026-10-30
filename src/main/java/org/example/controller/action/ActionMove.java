package org.example.controller.action;

import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.*;

public class ActionMove extends Action {
    private final Model model;
    private MyShape currentShape;
    private int totalDx = 0;
    private int totalDy = 0;

    public ActionMove(Model model) {
        this.model = model;
    }

    public void moveShape(Point point) {
        if (pressedPoint != null && currentShape != null) {
            int dx = (int) (point.x - pressedPoint.getX());
            int dy = (int) (point.y - pressedPoint.getY());

            totalDx += dx;
            totalDy += dy;

            Point firstPoint = new Point((Point) currentShape.getFirstPoint());
            Point secondPoint = new Point((Point) currentShape.getSecondPoint());
            firstPoint.translate(dx, dy);
            secondPoint.translate(dx, dy);

            currentShape.setFrame(firstPoint, secondPoint);
            pressedPoint = point;
        }
    }

    public void chooseShape(Point point) {
        currentShape = null;
        if (model.getShapesList().isEmpty()) {
            return;
        }

        for (int i = model.getShapesList().size() - 1; i > -1; i--) {
            MyShape shape = model.getShapesList().get(i);
            if (isCursorOnShape(shape, point)) {
                currentShape = shape;
                pressedPoint = point;
            }
        }
    }

    private boolean isCursorOnShape(MyShape shape, Point point) {
        if (shape == null || point == null) {
            return false;
        }

        boolean checkX = shape.getFirstPoint().getX() < point.x
                && shape.getSecondPoint().getX() > point.x
                ||
                shape.getFirstPoint().getX() > point.x
                        && shape.getSecondPoint().getX() < point.x;
        boolean checkY = shape.getFirstPoint().getY() < point.y
                && shape.getSecondPoint().getY() > point.y
                ||
                shape.getFirstPoint().getY() > point.y
                        && shape.getSecondPoint().getY() < point.y;
        return checkY && checkX;
    }

    @Override
    public void mousePressed(Point point) {
        this.chooseShape(point);
    }

    @Override
    public void mouseDragged(Point point) {
        this.moveShape(point);
    }

    @Override
    public void execute() {
        if (currentShape != null) {
            shiftShape(totalDx, totalDy);
        }
        model.notifyObservers();
    }

    @Override
    public void unexecute() {
        if (currentShape != null) {
            shiftShape(-totalDx, -totalDy);
        }
        model.notifyObservers();
    }

    private void shiftShape(int dx, int dy) {
        Point firstPoint = new Point((Point) currentShape.getFirstPoint());
        Point seconfPoint = new Point((Point) currentShape.getSecondPoint());
        firstPoint.translate(dx, dy);
        seconfPoint.translate(dx, dy);
        currentShape.setFrame(firstPoint, seconfPoint);
    }

}
