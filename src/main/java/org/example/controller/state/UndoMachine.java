package org.example.controller.state;

import org.example.controller.action.Action;

import java.util.LinkedList;

public class UndoMachine {
    private UndoRedoState undoRedoState;

    public UndoMachine() {
        LinkedList<Action> undoList = new LinkedList<>();
        LinkedList<Action> redoList = new LinkedList<>();
        undoRedoState = new StateDisableUndoDisableRedo(undoList, redoList);
    }

    public void executeRedo() {
        undoRedoState = undoRedoState.redo();
    }

    public void executeUndo() {
        undoRedoState = undoRedoState.undo();
    }

    public boolean isEnableUndo() {
        return !undoRedoState.getUndoActivityList().isEmpty();
    }


    public boolean isEnableRedo() {
        return !undoRedoState.getRedoActivityList().isEmpty();
    }

    public void add(Action action) {
        undoRedoState.clearHistory();
        undoRedoState.addAction(action);
        undoRedoState = new StateEnableUndoDisableRedo(undoRedoState.getUndoActivityList(), undoRedoState.getRedoActivityList());
    }

    public void loadFromFile() {
        undoRedoState.clearAll();
        undoRedoState = new StateDisableUndoDisableRedo(undoRedoState.getUndoActivityList(), undoRedoState.getRedoActivityList());
    }
}
