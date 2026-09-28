package org.example.controller.serialize;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.awt.*;
import java.lang.reflect.Type;

public class ColorSerializer implements JsonSerializer<Color>, JsonDeserializer<Color> {
    public static final String RED = "red";
    public static final String GREEN = "green";
    public static final String BLUE = "blue";
    @Override
    public JsonElement serialize(Color src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(RED, String.valueOf(src.getRed()));
        jsonObject.addProperty(GREEN, String.valueOf(src.getGreen()));
        jsonObject.addProperty(BLUE, String.valueOf(src.getBlue()));
        return jsonObject;
    }

    @Override
    public Color deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();
        int red = jsonObject.get(RED).getAsInt();
        int green = jsonObject.get(GREEN).getAsInt();
        int blue = jsonObject.get(BLUE).getAsInt();
        return new Color(red, green, blue);
    }
}
