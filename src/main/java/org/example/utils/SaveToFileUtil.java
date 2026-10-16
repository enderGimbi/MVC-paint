package org.example.utils;

import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.*;
import java.io.*;
import java.util.List;

public class SaveToFileUtil {
    private final Model model;
    private final File file = new File("src/main/resources/shapes.dat");


    public SaveToFileUtil(Model model) {
        this.model = model;
        if(!file.exists()){
            try {
                file.createNewFile();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void save(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
            oos.writeObject(model.getShapesList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void load(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            var list = (List<MyShape>)ois.readObject();
            if(list!=null){
                model.setShapesList(list);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
