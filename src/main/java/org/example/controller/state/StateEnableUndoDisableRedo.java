package org.example.controller.state;


import org.example.controller.action.Action;

import java.util.LinkedList;

public class StateEnableUndoDisableRedo extends UndoRedoState {

    protected StateEnableUndoDisableRedo(LinkedList<Action> undoActivityList, LinkedList<Action> redoActivity) {
        super(undoActivityList, redoActivity);
    }

    @Override
    public UndoRedoState undo() {
        LinkedList<Action> undoActivityList = getUndoActivityList();
        LinkedList<Action> redoActivityList = getRedoActivityList();
        Action action = undoActivityList.pollLast();
        if (action != null) {
            redoActivityList.add(action);
            action.unexecute();
        }

        return determineNextState();
    }

    @Override
    public UndoRedoState redo() {
        return this;
    }
}
