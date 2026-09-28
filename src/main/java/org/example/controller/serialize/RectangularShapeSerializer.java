package org.example.controller.serialize;

import com.google.gson.*;

import java.awt.geom.*;
import java.lang.reflect.Type;

public class RectangularShapeSerializer implements JsonSerializer<RectangularShape>, JsonDeserializer<RectangularShape> {

    public static final String TYPE_PARAM = "type";
    public static final String SHAPE_PARAM = "shape";
    public static final String X_PARAM = "x";
    public static final String Y_PARAM = "y";
    public static final String WIDTH_PARAM = "width";
    public static final String HEIGHT_PARAM = "height";

    public static final String RECTANGULAR = "java.awt.geom.Rectangle2D$Double";
    public static final String ROUND_RECTANGULAR = "java.awt.geom.RoundRectangle2D$Double";
    public static final String ELLIPSE = "java.awt.geom.Ellipse2D$Double";

    @Override
    public JsonElement serialize(RectangularShape recShape, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty(TYPE_PARAM, recShape.getClass().getName());
        Rectangle2D rectangle2D = recShape.getFrame();
        Gson gson = new Gson();
        object.add(SHAPE_PARAM, gson.toJsonTree(rectangle2D));
        return object;
    }

    @Override
    public RectangularShape deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        String className = obj.get(TYPE_PARAM).getAsString();
        JsonObject shapeJson = obj.getAsJsonObject(SHAPE_PARAM);
        try {
            double x = shapeJson.get(X_PARAM).getAsDouble();
            double y = shapeJson.get(Y_PARAM).getAsDouble();
            double width = shapeJson.get(WIDTH_PARAM).getAsDouble();
            double height = shapeJson.get(HEIGHT_PARAM).getAsDouble();

            RectangularShape shape;
            switch (className) {
                case RECTANGULAR -> shape = new Rectangle2D.Double(x, y, width, height);
                case ROUND_RECTANGULAR -> shape = new RoundRectangle2D.Double(x, y, width, height, 0, 0);
                case ELLIPSE -> shape = new Ellipse2D.Double(x, y, width, height);
                default -> shape = new Arc2D.Double(Arc2D.PIE);
            }
            return shape;
        } catch (Exception e) {
            System.err.println("Не удалось преобразовать json: " + e.getMessage());
            throw new JsonParseException(e);
        }
    }
}
