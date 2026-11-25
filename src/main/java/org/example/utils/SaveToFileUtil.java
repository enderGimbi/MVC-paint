package org.example.utils;

import org.example.model.Model;
import org.example.model.MyShape;
import tools.jackson.databind.json.JsonMapper;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class SaveToFileUtil {
    private final Model model;
    private final Component parent;
    private Path file = Paths.get("data", "shapes.json");


    public SaveToFileUtil(Model model, Component parent) {
        this.model = model;
        this.parent = parent;
    }

    private boolean chooseFile(boolean saveDialog){
        JFileChooser chooser = new JFileChooser(file.toFile());
        int result = saveDialog
                ? chooser.showSaveDialog(parent)
                : chooser.showOpenDialog(parent);
        if(result!=JFileChooser.APPROVE_OPTION){
            return false;
        }
        Path selected = chooser.getSelectedFile().toPath();
        if(saveDialog && !selected.toString().endsWith(".json")){
            selected = Path.of(selected+".json");
        };
        this.file = selected;
        return true;
    }

    public void save() {
        if(!chooseFile(true)){
            return;
        }
        JsonMapper mapper = JsonMapper.builder().build();
        try (BufferedWriter writer = Files.newBufferedWriter(
                file,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE
        )) {
            mapper.writeValue(writer, model.getShapesList());
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при записи в файл");
        }
    }

    public void load() {
        if(!chooseFile(false)){
            return;
        }
        if (!Files.exists(file))
            return;
        JsonMapper mapper = JsonMapper.builder().build();
        try (var reader = Files.newBufferedReader(file)) {
            List<MyShape> load = mapper.readerForListOf(MyShape.class).readValue(reader);
            model.setShapesList(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
