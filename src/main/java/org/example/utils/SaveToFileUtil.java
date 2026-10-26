package org.example.utils;

import org.example.model.Model;
import org.example.model.MyShape;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

import java.awt.*;
import java.io.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class SaveToFileUtil {
    private Model model;
    private final Path file = Paths.get("data","shapes.json");


    public SaveToFileUtil(Model model) throws IOException {
        this.model = model;
        if(file.getParent()!=null){
            Files.createDirectories(file.getParent());
        }
    }

    public void save(){
        JsonMapper mapper = JsonMapper.builder().build();
        try(BufferedWriter writer = Files.newBufferedWriter(
                file,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE
                )) {
            mapper.writeValue(writer,model.getShapesList());
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при записи в файл");
        }
    }
    public void load(){
        if(!Files.exists(file))
            return;
        JsonMapper mapper = JsonMapper.builder().build();
        try(var reader = Files.newBufferedReader(file)) {
            List<MyShape> load =mapper.readerForListOf(MyShape.class).readValue(reader);
            model.setShapesList(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
