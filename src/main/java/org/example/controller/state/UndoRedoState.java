package org.example.controller.state;

import org.example.controller.action.Action;

import java.util.LinkedList;

public abstract class UndoRedoState {
    private static final int MAX_UNDO = 50;
    private final LinkedList<Action> undoActivityList;
    private final LinkedList<Action> redoActivityList;

    protected UndoRedoState(LinkedList<Action> undoActivityList, LinkedList<Action> redoActivity) {
        this.undoActivityList = undoActivityList;
        this.redoActivityList = redoActivity;
    }

    public abstract UndoRedoState undo();
    public abstract UndoRedoState redo();

    public void clearHistory(){
        redoActivityList.clear();
    }
    public void addAction(Action action){
        if(undoActivityList.size() != MAX_UNDO){
            undoActivityList.add(action);
        }
    }

    public LinkedList<Action> getUndoActivityList() {
        return undoActivityList;
    }

    public LinkedList<Action> getRedoActivityList() {
        return redoActivityList;
    }

    protected UndoRedoState determineNextState() {
        boolean hasUndo = !getUndoActivityList().isEmpty();
        boolean hasRedo = !getRedoActivityList().isEmpty();

        if (hasUndo && hasRedo) {
            return new StateEnableUndoEnableRedo(getUndoActivityList(), getRedoActivityList());
        } else if (hasUndo && !hasRedo) {
            return new StateEnableUndoDisableRedo(getUndoActivityList(), getRedoActivityList());
        } else if (!hasUndo && hasRedo) {
            return new StateDisableUndoEnableRedo(getUndoActivityList(), getRedoActivityList());
        } else {
            return new StateDisableUndoDisableRedo(getUndoActivityList(), getRedoActivityList());
        }
    }
}
