package Listeners;

import TableObjects.CellData;
import TableObjects.ViewStates;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TableListenerTest {

    @Test
    void switchViewStateTest()
    {
        String[] columnNames = {"$A", "$B", "$C", "$D", "$E", "$F", "$G", "$H", "$I"};
        Object[][] initialData = {{"","","","","","","","",""}};
        JTable table = new JTable(initialData,columnNames);
        TableListener testListener = new TableListener();
        testListener.setTableModel(table.getModel());

        //Create a table to be value view
        List<CellData> cellDataList = new ArrayList<>();
        CellData newCell;
        int columnNumber = 0;
        for (String columnName: columnNames)
        {
            newCell = new CellData();
            newCell.setRowAndColumn(0,columnNumber);
            newCell.setColumnName(columnName);
            newCell.setTableListener(testListener);
            newCell.setValueData(Integer.toString(columnNumber));
            newCell.setEquationData(String.format("%s %d +",columnName,columnNumber));
            columnNumber++;
            cellDataList.add(newCell);
        }
        testListener.setCellDataList(cellDataList);
        testListener.setColumnNames(Arrays.asList(columnNames));

        testListener.switchViewState(ViewStates.ValueView);
        TableModel model = table.getModel();
        int[] rowAndColumn;
        for (CellData cell: cellDataList)
        {
            rowAndColumn = cell.getRowAndColumn();
            assertEquals(cell.getValueData(),model.getValueAt(rowAndColumn[0],rowAndColumn[1]));
        }

        testListener.switchViewState(ViewStates.EquationView);
        for (CellData cell: cellDataList)
        {
            rowAndColumn = cell.getRowAndColumn();
            assertEquals(cell.getEquationData(),model.getValueAt(rowAndColumn[0],rowAndColumn[1]));
        }
    }

    @Test
    void undoCellStateTest()
    {
        String[] columnNames = {"$A", "$B", "$C"};
        Object[][] initialData = {{"","", ""}};
        JTable table = new JTable(initialData,columnNames);
        TableListener testListener = new TableListener();
        testListener.setTableModel(table.getModel());
        TableModel model = table.getModel();

        //Create a table to be value view
        List<CellData> cellDataList = new ArrayList<>();
        CellData newCell;
        int columnNumber = 0;
        for (String columnName: columnNames)
        {
            newCell = new CellData();
            newCell.setRowAndColumn(0,columnNumber);
            newCell.setColumnName(columnName);
            newCell.setTableListener(testListener);
            newCell.setValueData(Integer.toString(columnNumber));
            newCell.setEquationData(String.format("%s %d +",columnName,columnNumber));
            columnNumber++;
            cellDataList.add(newCell);
        }
        testListener.setCellDataList(cellDataList);
        testListener.setColumnNames(Arrays.asList(columnNames));

        TableModelEvent event = new TableModelEvent(model,0,0,1);
        testListener.tableChanged(event);

        CellData editedCell = cellDataList.get(1);
        assertEquals(editedCell.getValueData(),testListener.getCellData(0,1).getValueData());
        assertEquals(editedCell.getEquationData(),testListener.getCellData(0,1).getEquationData());

        testListener.undoCellState();

        editedCell.setValueData("Error");
        assertEquals(editedCell.getValueData(),testListener.getCellData(0,1).getValueData());
        assertEquals(editedCell.getEquationData(),testListener.getCellData(0,1).getEquationData());
    }

    @Test
    void getCellDataNullTest()
    {
        String[] columnNames = {"$A", "$B", "$C"};
        Object[][] initialData = {{"","", ""}};
        JTable table = new JTable(initialData,columnNames);
        TableListener testListener = new TableListener();
        testListener.setTableModel(table.getModel());
        TableModel model = table.getModel();

        //Create a table to be value view
        List<CellData> cellDataList = new ArrayList<>();
        CellData newCell;
        int columnNumber = 0;
        for (String columnName: columnNames)
        {
            newCell = new CellData();
            newCell.setRowAndColumn(0,columnNumber);
            newCell.setColumnName(columnName);
            newCell.setTableListener(testListener);
            newCell.setValueData(Integer.toString(columnNumber));
            newCell.setEquationData(String.format("%s %d +",columnName,columnNumber));
            columnNumber++;
            cellDataList.add(newCell);
        }

        assertNull(testListener.getCellData(0,0));
        testListener.setCellDataList(cellDataList);
        testListener.setColumnNames(Arrays.asList(columnNames));

        assertNull(testListener.getCellData(0,12.5));

        TableModelEvent event = new TableModelEvent(model,0,0,1);
        model.setValueAt("",0,1);
        testListener.tableChanged(event);

        testListener.switchViewState(ViewStates.EquationView);
        model.setValueAt("$A $C +",0,1);
        testListener.tableChanged(event);

        event = new TableModelEvent(model,0,0,2);
        model.setValueAt("$B sin",0,2);
        testListener.tableChanged(event);

        assertEquals("Error",testListener.getCellData(0,1).getValueData());
        assertEquals("Error",testListener.getCellData(0,2).getValueData());
    }

}