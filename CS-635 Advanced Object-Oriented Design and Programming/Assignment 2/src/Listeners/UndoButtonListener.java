package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UndoButtonListener implements ActionListener {

    private TableListener tableListener;
    public UndoButtonListener(){}

    public void setTableListener(TableListener listener){ tableListener= listener; }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        tableListener.undoCellState();
    }
}
