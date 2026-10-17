package org.example.controller.action;

import java.util.ArrayDeque;
import java.util.Deque;

public class ActionsHistory {
    private final int MAX_MEMORY = 25;

    private final Deque<Action> undoStack = new ArrayDeque<>();
    private final Deque<Action> redoStack = new ArrayDeque<>();

    public void saveState(Action action) {
        redoStack.clear();

        action.execute();

        undoStack.push(action);

        if (undoStack.size() > MAX_MEMORY) {
            trimUndoStack();
        }
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Action action = undoStack.pop();
            action.unexecute();
            redoStack.push(action);
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Action action = redoStack.pop();
            action.execute();
            undoStack.push(action);
        }
    }

    private void trimUndoStack() {
        if (undoStack.size() > MAX_MEMORY) {
            Deque<Action> temp = new ArrayDeque<>();
            while (undoStack.size() > 1) {
                temp.push(undoStack.pop());
            }
            undoStack.pop();
            while (!temp.isEmpty()) {
                undoStack.push(temp.pop());
            }
        }
    }

    public void loadFromFile() {
        redoStack.clear();
        undoStack.clear();
    }

    public boolean isRedoEmpty(){
        return redoStack.isEmpty();
    }
    public boolean isUndoEmpty(){
        return undoStack.isEmpty();
    }

}