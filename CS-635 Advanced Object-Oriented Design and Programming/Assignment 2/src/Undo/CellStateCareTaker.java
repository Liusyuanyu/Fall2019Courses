package Undo;

import java.util.Stack;

public class CellStateCareTaker
{
    Stack<Object> mementoStack;

    public CellStateCareTaker()
    {
        mementoStack = new Stack<>();
    }
    public void save(CellStateOriginator originator)
    {
        mementoStack.add(originator.createMemento());
    }
    public void undo(CellStateOriginator originator)
    {
        if(mementoStack.isEmpty())
            originator.undoToLastSave(null);
        else
            originator.undoToLastSave(mementoStack.pop());
    }

}
