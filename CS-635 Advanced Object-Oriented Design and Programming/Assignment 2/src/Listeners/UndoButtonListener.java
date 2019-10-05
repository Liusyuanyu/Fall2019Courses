package Listeners;

import TableObjects.CellData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UndoButtonListener implements ActionListener {

    TableListener tableListener;
    public UndoButtonListener(){}

    public void setTableListener(TableListener listener){ tableListener= listener; }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        tableListener.undoCellState();
    }
}
