package Listeners;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwitchViewButtonListener implements ActionListener
{
    JTable valueTable;
    JTable equationTable;

    JScrollPane scrollPane;

    public SwitchViewButtonListener()
    {
        //Nothing
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(valueTable==null || equationTable==null)
            return;
        Component currentViewport = scrollPane.getViewport().getView();
        String name = currentViewport.getName();
        if(currentViewport.getName()=="Value"){
            scrollPane.remove(currentViewport);
            scrollPane.setViewportView(equationTable);
            scrollPane.updateUI();
        }
        else
        {
            scrollPane.remove(currentViewport);
            scrollPane.setViewportView(valueTable);
            scrollPane.updateUI();
        }

    }

    public boolean setTables(JTable value, JTable equation)
    {
        if(value ==null || equation==null)
            return false;
        valueTable = value;
        equationTable= equation;

        return true;
    }

    public boolean setSrollPane(JScrollPane scrollPane)
    {
        if(scrollPane ==null)
            return false;
        this.scrollPane = scrollPane;
        return true;
    }
}
