package org.example.controller.action;

import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.*;
import java.awt.geom.Point2D;

public class ActionMove {
    private final Model model;
    private MyShape currentShape;
    private Point pressedPoint;

    public ActionMove(Model model) {
        this.model = model;
    }

    public void moveShape(Point point){
        if(pressedPoint!=null&&currentShape!=null){
            int dx = point.x-pressedPoint.x;
            int dy = point.y-pressedPoint.y;

            Point firstPoint = new Point((Point) currentShape.getFirstPoint());
            Point secondPoint = new Point((Point) currentShape.getSecondPoint());
            firstPoint.translate(dx,dy);
            secondPoint.translate(dx,dy);

            currentShape.setFrame(firstPoint,secondPoint);
            pressedPoint=point;
        }
    }

    public void chooseShape(Point point){
        currentShape=null;
        if(model.getShapesList().isEmpty()){
            return;
        }
        MyShape shape;

        for (int i = model.getShapesList().size()-1; i >-1; i--) {
            shape = model.getShapesList().get(i);
            if(isCursorOnShape(shape,point)){
                currentShape = shape;
                pressedPoint = point;
            }
        }
    }

    private boolean isCursorOnShape(MyShape shape,Point point){
        if(shape==null||point==null)
            return false;
        boolean checkX = shape.getFirstPoint().getX()<point.x&&shape.getSecondPoint().getX()>point.x;
        boolean checkY = shape.getFirstPoint().getY()<point.y&&shape.getSecondPoint().getY()>point.y;
        return checkY && checkX;
    }
}
