import javax.swing.*;
import javax.swing.table.TableModel;

import Listeners.SpreadSheetListener;
import Listeners.SwitchViewButtonListener;

import java.awt.*;

public class SpreadSheetGUI {
    public static void main(String[] args)
    {
        //Create a table to be value view
        String[] columnNames = {"$A", "$B", "$C", "$D", "$E", "$F", "$G", "$H", "$I"};
        Object[][] data = {{"12"," "," "," "," "," "," ", " "," "}};

        JTable valueTable = new JTable(data, columnNames);
        TableModel valueTableModel = valueTable.getModel();
        valueTable.setFillsViewportHeight(true);
        SpreadSheetListener valueTableListener = new SpreadSheetListener();
        valueTableModel.addTableModelListener(valueTableListener);
        valueTable.setName("Value");

        JTable equationTable = new JTable(data, columnNames);
        TableModel equationTableModel = equationTable.getModel();
        equationTable.setFillsViewportHeight(true);
        SpreadSheetListener equationTableListener = new SpreadSheetListener();
        equationTableModel.addTableModelListener(equationTableListener);
        equationTable.setName("Equation");

        //Create buttons
        JButton switchView =new JButton("Switch View");//Switch button
        SwitchViewButtonListener switchButtonListener = new SwitchViewButtonListener();
        switchView.addActionListener(switchButtonListener);

        JButton undo =new JButton("Undo");//Undo button
//        SpreadSheetListener valueTableListener = new SpreadSheetListener();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVisible(true);
        scrollPane.setViewportView(valueTable);

        switchButtonListener.setSrollPane(scrollPane);
        switchButtonListener.setTables(valueTable,equationTable);

        JFrame frame=new JFrame();//creating instance of JFrame
        frame.setTitle("SpreadSheet");
        frame.setLayout(new GridBagLayout());
        frame.setSize(600,120);//400 width and 500 height
        frame.setLocation(100,200);
        frame.setVisible(true);//making the frame visible
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagConstraints bagConstraints = new GridBagConstraints();
        bagConstraints.fill = GridBagConstraints.WEST;
        bagConstraints.weightx = 0.5;
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 0;
        frame.add(switchView,bagConstraints);//adding button in JFrame

        bagConstraints.fill = GridBagConstraints.WEST;
        bagConstraints.weightx = 0.5;
        bagConstraints.gridx = 1;
        bagConstraints.gridy = 0;
        frame.add(undo,bagConstraints);//adding button in JFrame

        bagConstraints.fill = GridBagConstraints.HORIZONTAL;
        bagConstraints.ipady = 40;
        bagConstraints.weightx = 1;
        bagConstraints.gridwidth = 2;
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 1;
        frame.add(scrollPane,bagConstraints);//adding View table in JFrame
    }

}