package Listeners;

import TableObjects.ViewStates;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwitchViewButtonListener implements ActionListener
{
    private JLabel viewModeLabel;
    private TableListener tableListener;
    private ViewStates currentViewMode;

    public SwitchViewButtonListener(){}

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(currentViewMode == ViewStates.ValueView)
        {
            viewModeLabel.setText("Equation View");
            currentViewMode = ViewStates.EquationView;
            tableListener.switchViewState(currentViewMode);
        }
        else//ViewStates.EquationView
        {
            viewModeLabel.setText("Value View");
            currentViewMode = ViewStates.ValueView;
            tableListener.switchViewState(currentViewMode);
        }
    }

    public void setViewModeLabel(JLabel viewModeLabel) {
        this.viewModeLabel = viewModeLabel;
    }
    public void setTableListener(TableListener listener)
    {
        tableListener = listener;
    }
    public void setCurrentViewMode(ViewStates mode)
    {
        currentViewMode = mode;
    }
}
