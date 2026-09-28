package org.example.controller.serialize;

import com.google.gson.*;
import org.example.model.shape.fill.Fill;
import org.example.model.shape.fill.FillBehavior;
import org.example.model.shape.fill.NoFill;

import java.awt.*;
import java.awt.geom.RectangularShape;
import java.lang.reflect.Type;

public class FillSerializer implements JsonSerializer<FillBehavior>, JsonDeserializer<FillBehavior> {
    public static final String TYPE_PARAM = "type";
    public static final String FILL_PARAM = "fb";
    public static final String FILL_TYPE = "org.example.model.shape.fill.Fill";
    public static final String NO_FILL_TYPE = "org.example.model.shape.fill.NoFill";
    @Override
    public JsonElement serialize(FillBehavior fillBehavior, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(TYPE_PARAM, fillBehavior.getClass().getName());
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(RectangularShape.class, new RectangularShapeSerializer())
                .registerTypeAdapter(Color.class, new ColorSerializer())
                .create();
        jsonObject.add(FILL_PARAM, gson.toJsonTree(fillBehavior));
        return jsonObject;
    }
    @Override
    public FillBehavior deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        String className = object.get(TYPE_PARAM).getAsString();
        try {
            FillBehavior fillBehavior;
            switch (className) {
                case FILL_TYPE -> fillBehavior = new Fill();
                case NO_FILL_TYPE -> fillBehavior = new NoFill();
                default -> throw new IllegalStateException("Unexpected value: " + className);
            }
            return fillBehavior;
        } catch (Exception e) {
            System.err.println("Не удалось десериализовать заливку фигуры: " + e.getMessage());
            throw new JsonParseException(e);
        }
    }


}
