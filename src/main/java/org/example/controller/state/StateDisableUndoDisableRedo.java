package org.example.controller.state;

import org.example.controller.action.Action;

import java.util.LinkedList;

public class StateDisableUndoDisableRedo extends UndoRedoState {
    protected StateDisableUndoDisableRedo(LinkedList<Action> undoActivityList, LinkedList<Action> redoActivity) {
        super(undoActivityList, redoActivity);
    }

    @Override
    public UndoRedoState undo() {
        return this;
    }

    @Override
    public UndoRedoState redo() {
        return this;
    }
}
