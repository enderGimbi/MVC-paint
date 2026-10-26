package org.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.example.model.fill.Fill;
import org.example.model.fill.FillBehavior;
import org.example.model.fill.NoFill;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.io.Serializable;

public class MyShape implements Cloneable, Serializable {
    @Getter
    private String shapeType;
    @Getter
    private int colorRgb;
    @Getter
    private boolean filled;

    @Getter
    @Setter
    private Point2D firstPoint;

    @Getter
    @Setter
    private Point2D secondPoint;
    @Getter
    @JsonIgnore
    private Color color;
    @Getter
    @JsonIgnore
    private RectangularShape shape;
    @Getter
    @JsonIgnore
    private FillBehavior fb;

    public MyShape() {
        color = Color.BLUE;
        shape = new Rectangle2D.Double();
        fb = new Fill();
        fb.setColor(color);
        fb.setShape(shape);

        this.shapeType = "RECTANGLE";
        this.colorRgb = color.getRGB();
        this.filled = true;
    }

    public MyShape(Shape shape, Color color, FillBehavior fill) {
        this.shape = (RectangularShape) shape;
        this.color = color;
        fb = fill;
        fb.setColor(color);
        fb.setShape((RectangularShape) shape);

        //для json
        this.shapeType = (shape instanceof Ellipse2D)?"ELLIPSE":"RECTANGLE";
        this.colorRgb = color.getRGB();
        this.filled = !(fill instanceof NoFill);
    }

    @JsonCreator
    public MyShape(
            @JsonProperty("shapeType") String shapeType,
            @JsonProperty("colorRgb") int colorRGB,
            @JsonProperty("filled") boolean filled,
            @JsonProperty("firstPoint") Point2D.Double firstPoint,
            @JsonProperty("secondPoint") Point2D.Double secondPoint)
    {
        this.shapeType = shapeType!=null?shapeType:"RECTANGLE";
        this.colorRgb = colorRGB;
        this.filled = filled;
        this.firstPoint = firstPoint!=null?firstPoint:new Point2D.Double();
        this.secondPoint = secondPoint!=null?secondPoint:new Point2D.Double();

        this.color = new Color(colorRGB);
        this.shape = "ELLIPSE".equalsIgnoreCase(shapeType)?new Ellipse2D.Double():new Rectangle2D.Double();

        this.fb = filled?new Fill():new NoFill();
        this.fb.setColor(this.color);
        this.fb.setShape(this.shape);
        setFrame(this.getFirstPoint(),this.getSecondPoint());
    }


    public void setFrame(Point2D x, Point2D y) {
        this.firstPoint = x;
        this.secondPoint = y;
        shape.setFrameFromDiagonal(x, y);
    }

    void draw(Graphics2D g) {
        fb.draw(g);
    }

    @Override
    public MyShape clone() {
        FillBehavior newFb;
        if (this.fb instanceof NoFill) {
            newFb = new NoFill();
        } else {
            newFb = new Fill();
        }
        newFb.setColor(this.color);

        Shape newGeom = (this.shape instanceof Ellipse2D) ? new Ellipse2D.Double() : new Rectangle2D.Double();
        MyShape cloned = new MyShape(newGeom, this.color, newFb);
        if (this.shape != null) {
            cloned.shape.setFrame(this.shape.getBounds2D());
            newFb.setShape(cloned.shape);
        }
        cloned.setFirstPoint(getFirstPoint());
        cloned.setSecondPoint(getSecondPoint());
        return cloned;
    }

}
