package org.example.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.controller.serialize.ColorSerializer;
import org.example.controller.serialize.FillSerializer;
import org.example.controller.serialize.RectangularShapeSerializer;
import org.example.model.Model;
import org.example.model.shape.fill.FillBehavior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.awt.geom.RectangularShape;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Controller
public class SaveLoadController {
    private Model model;

    public void save() {
        File file;
        file = new File("data.json");
        try (FileWriter writer = new FileWriter(file)) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(RectangularShape.class, new RectangularShapeSerializer())
                    .registerTypeAdapter(Color.class, new ColorSerializer())
                    .registerTypeHierarchyAdapter(FillBehavior.class, new FillSerializer())
                    .create();
            gson.toJson(model, Model.class, writer);
        } catch (IOException e) {
            System.err.println("Не удалось сохранить файл: " + e.getMessage());
            e.printStackTrace();
        }
//        File filePath = new File("data.bin");
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));) {
//            oos.writeObject(model);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void load() {
        File file = new File("data.json");
        try (FileReader reader = new FileReader(file)) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(RectangularShape.class, new RectangularShapeSerializer())
                    .registerTypeAdapter(Color.class, new ColorSerializer())
                    .registerTypeHierarchyAdapter(FillBehavior.class, new FillSerializer())
                    .create();
            Model newModel = gson.fromJson(reader, Model.class);
            model.reloadModel(newModel);
        } catch (IOException e) {
            System.err.println("Не удалось загрузить файл: " + e.getMessage());
            e.printStackTrace();
        }
//        File filePath = new File("data.bin");
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));) {
//            Model model = (Model) ois.readObject();
//            this.model.reloadModel(model);
//        } catch (IOException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Autowired
    public void setModel(Model model) {
        this.model = model;
    }
}
