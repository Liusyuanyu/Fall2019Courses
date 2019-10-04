package Listeners;

import TableObjects.ViewModes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwitchViewButtonListener implements ActionListener
{
    private JLabel viewModeLabel;
    private TableListener tableListener;
    private ViewModes currentViewMode;

    public SwitchViewButtonListener(){}

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(currentViewMode == ViewModes.ValueView)
        {
            viewModeLabel.setText("Equation View");
            currentViewMode = ViewModes.EquationView;
            tableListener.switchViewMode(currentViewMode);
        }
        else//ViewModes.EquationView
        {
            viewModeLabel.setText("Value View");
            currentViewMode = ViewModes.ValueView;
            tableListener.switchViewMode(currentViewMode);
        }
    }

    public void setViewModeLabel(JLabel viewModeLabel) {
        this.viewModeLabel = viewModeLabel;
    }
    public void setTableListener(TableListener listener)
    {
        tableListener = listener;
    }
    public void setCurrentViewMode(ViewModes mode)
    {
        currentViewMode = mode;
    }
}
