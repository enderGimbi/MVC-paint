package org.example.controller.action;

import org.example.model.MyShape;

import java.util.ArrayList;
import java.util.List;

public class ActionsHistory {
    private final List<List<MyShape>> history = new ArrayList<>();
    private int currentIndex=0;
    private final int MAX_MEMORY = 25;

    public ActionsHistory(){
        history.add(new ArrayList<>());
    }

    public void saveState(List<MyShape> shapes){
        while(history.size()>currentIndex+1){
            history.remove(history.size()-1);
        }

        List<MyShape> copy = deepCopy(shapes);

        history.add(copy);
        currentIndex++;

        if(history.size()>MAX_MEMORY){
            history.remove(0);
            currentIndex--;
        }
    }

    public List<MyShape> undo(){
        if(currentIndex > 0){
            currentIndex--;
            return deepCopy(history.get(currentIndex));
        }
        throw new NullPointerException("ЗА ПРЕДЕЛАМИ МАССИВА - ДО"+" "+currentIndex);
    }

    public List<MyShape> redo(){
        if(currentIndex < history.size()-1){
            currentIndex++;
            return deepCopy(history.get(currentIndex));
        }
        throw new NullPointerException("ЗА ПРЕДЕЛАМИ МАССИВА - ПОСЛЕ"+" "+currentIndex);
    }


    private List<MyShape> deepCopy(List<MyShape> shapes){
        List<MyShape> res = new ArrayList<>();
        for(MyShape shape : shapes){
            res.add(shape.clone());
        }
        return res;
    }


}
