package org.example.controller.state;

import org.example.controller.action.Action;

import java.util.LinkedList;

public class StateEnableUndoEnableRedo extends UndoRedoState {

    protected StateEnableUndoEnableRedo(LinkedList<Action> undoActivityList, LinkedList<Action> redoActivity) {
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
        if (undoActivityList.isEmpty()) {
            return new StateDisableUndoEnableRedo(getUndoActivityList(), getRedoActivityList());
        } else return this;
    }

    @Override
    public UndoRedoState redo() {
        LinkedList<Action> undoActivityList = getUndoActivityList();
        LinkedList<Action> redoActivityList = getRedoActivityList();
        Action action = redoActivityList.pollLast();
        if (action != null) {
            undoActivityList.add(action);
             action.execute();
        }
        return determineNextState();
    }
}
