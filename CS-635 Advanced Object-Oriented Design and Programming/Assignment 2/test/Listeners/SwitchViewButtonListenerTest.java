package Listeners;

import TableObjects.ViewStates;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.event.ActionEvent;
import static org.junit.jupiter.api.Assertions.*;

class SwitchViewButtonListenerTest {

    @Test
    void switchViewButtonListenerTest()
    {
        String[] columnNames = {"$A", "$B"};
        Object[][] initialData = {{"",""}};
        JTable table = new JTable(initialData,columnNames);
        TableListener listener = new TableListener();
        listener.setTableModel(table.getModel());

        JLabel label = new JLabel();
        label.setText("Value View");

        SwitchViewButtonListener testListener = new SwitchViewButtonListener();
        testListener.setTableListener(listener);
        testListener.setViewModeLabel(label);
        testListener.setCurrentViewState(ViewStates.ValueView);

        assertEquals("Value View",label.getText());
        testListener.actionPerformed(new ActionEvent(0,0,""));
        assertEquals("Equation View",label.getText());
        testListener.actionPerformed(new ActionEvent(0,0,""));
        assertEquals("Value View",label.getText());
    }

}