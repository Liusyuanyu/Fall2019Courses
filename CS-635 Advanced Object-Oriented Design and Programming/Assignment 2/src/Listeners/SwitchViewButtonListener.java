package Listeners;

import TableObjects.ViewStates;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwitchViewButtonListener implements ActionListener
{
    private JLabel viewModeLabel;
    private TableListener tableListener;
    private ViewStates currentViewState;

    public SwitchViewButtonListener(){}

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(currentViewState == ViewStates.ValueView)
        {
            viewModeLabel.setText("Equation View");
            currentViewState = ViewStates.EquationView;
            tableListener.switchViewState(currentViewState);
        }
        else//ViewStates.EquationView
        {
            viewModeLabel.setText("Value View");
            currentViewState = ViewStates.ValueView;
            tableListener.switchViewState(currentViewState);
        }
    }

    public void setViewModeLabel(JLabel viewModeLabel) {
        this.viewModeLabel = viewModeLabel;
    }
    public void setTableListener(TableListener listener)
    {
        tableListener = listener;
    }
    public void setCurrentViewState(ViewStates state)
    {
        currentViewState = state;
    }
}
