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
            // action.unexecute();
        }

        if (undoActivityList.isEmpty()) {
            if (redoActivityList.isEmpty()) {
                return new StateDisableUndoDisableRedo(undoActivityList, redoActivityList);
            } else {
                return new StateDisableUndoEnableRedo(undoActivityList, redoActivityList);
            }
        } else {
            if (redoActivityList.isEmpty()) {
                return this;
            } else {
                return new StateEnableUndoEnableRedo(undoActivityList, redoActivityList);
            }
        }
    }

    @Override
    public UndoRedoState redo() {
        return this;
    }
}
