package org.example.controller.state;

import org.example.controller.action.Action;

import java.util.LinkedList;

public class StateDisableUndoEnableRedo extends UndoRedoState{

    protected StateDisableUndoEnableRedo(LinkedList<Action> undoActivityList, LinkedList<Action> redoActivity) {
        super(undoActivityList, redoActivity);
    }

    @Override
    public UndoRedoState undo() {
        return this;
    }

    @Override
    public UndoRedoState redo() {
        LinkedList<Action> undoActivityList = getUndoActivityList();
        LinkedList<Action> redoActivityList = getRedoActivityList();
        Action action = redoActivityList.pollLast();
        if (action != null) {
            undoActivityList.add(action);
//            action.execute();
        }

        if (!redoActivityList.isEmpty()) {
            return new StateEnableUndoEnableRedo(getUndoActivityList(), getRedoActivityList());
        }
        else {
            return new StateEnableUndoDisableRedo(getUndoActivityList(),getRedoActivityList());
        }
    }

}
