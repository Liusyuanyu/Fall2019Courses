import javax.swing.*;
import javax.swing.table.TableModel;

import Listeners.TableListener;
import Listeners.SwitchViewButtonListener;
import Listeners.UndoButtonListener;
import TableObjects.CellData;
import TableObjects.ViewStates;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

class SpreadSheetGUI {

    public static void main(String[] args)
    {
        String[] columnNames = {"$A", "$B", "$C", "$D", "$E", "$F", "$G", "$H", "$I"};
        Object[][] initialData = {{"","","","","","","","",""}};

        JTable spreadSheetTable = new JTable(initialData, columnNames);
        TableModel spreadSheetTableModel = spreadSheetTable.getModel();
        TableListener tableListener = new TableListener();
        spreadSheetTableModel.addTableModelListener(tableListener);
        spreadSheetTable.setFillsViewportHeight(true);
        tableListener.setColumnNames(Arrays.asList(columnNames));
        tableListener.setTableModel(spreadSheetTableModel);

        JScrollPane scrollPane = new JScrollPane(spreadSheetTable);

        //Create a table to be value view
        java.util.List<CellData> cellDataList = new ArrayList<>();
        CellData newCell;
        int columnNumber = 0;
        for (String columnName: columnNames)
        {
            newCell = new CellData();
            newCell.setRowAndColumn(0,columnNumber);
            newCell.setColumnName(columnName);
            newCell.setTableListener(tableListener);
            columnNumber++;
            cellDataList.add(newCell);
        }

        //set Cell list after initial cells
        tableListener.setCellDataList(cellDataList);

        //Create a label
        JLabel viewModeLabel = new JLabel("Value View");
        viewModeLabel.setMinimumSize(new Dimension(100,30));

        //Create an Undo button
        JButton undo =new JButton("Undo");
        UndoButtonListener undoActionListener = new UndoButtonListener();
        undo.addActionListener(undoActionListener);
        undoActionListener.setTableListener(tableListener);

        //Create Switch button
        JButton switchView =new JButton("Switch View");
        SwitchViewButtonListener switchButtonListener = new SwitchViewButtonListener();
        switchView.addActionListener(switchButtonListener);
        switchButtonListener.setTableListener(tableListener);
        switchButtonListener.setViewModeLabel(viewModeLabel);
        switchButtonListener.setCurrentViewState(ViewStates.ValueView);

        JFrame frame=new JFrame();//creating instance of JFrame
        frame.setTitle("SpreadSheet");
        frame.setLayout(new GridBagLayout());
        frame.setSize(600,120);
        frame.setVisible(true);//making the frame visible
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagConstraints bagConstraints = new GridBagConstraints();
        bagConstraints.fill = GridBagConstraints.WEST;
        bagConstraints.weightx = 0.6;
        bagConstraints.gridx = 0;
        frame.add(viewModeLabel,bagConstraints);//adding label in JFrame

        bagConstraints.fill = GridBagConstraints.CENTER;
        bagConstraints.weightx = 0.2;
        bagConstraints.gridx = 1;
        frame.add(switchView,bagConstraints);//adding button in JFrame

        bagConstraints.fill = GridBagConstraints.EAST;
        bagConstraints.gridx = 2;
        frame.add(undo,bagConstraints);//adding button in JFrame

        bagConstraints.fill = GridBagConstraints.HORIZONTAL;
        bagConstraints.ipady = 40;
        bagConstraints.weightx = 1;
        bagConstraints.gridwidth = 3;
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 1;
        frame.add(scrollPane,bagConstraints);//adding View table in JFrame
    }
}