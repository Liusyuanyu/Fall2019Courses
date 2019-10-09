package TableObjects;

import Listeners.TableListener;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CellDataTest {

    private JTable table;
    private TableListener listener;
    private java.util.List<CellData> cellDataList;

    public void SimulateTableAndCellData()
    {
        String[] columnNames = {"$A", "$B", "$C", "$D", "$E", "$F", "$G", "$H", "$I"};
        Object[][] initialData = {{"","","","","","","","",""}};
        table = new JTable(initialData,columnNames);
        listener = new TableListener();
        listener.setTableModel(table.getModel());

        //Create a table to be value view
        cellDataList = new ArrayList<>();
        CellData newCell;
        int columnNumber = 0;
        for (String columnName: columnNames)
        {
            newCell = new CellData();
            newCell.setRowAndColumn(0,columnNumber);
            newCell.setColumnName(columnName);
            newCell.setTableListener(listener);
            columnNumber++;
            cellDataList.add(newCell);
        }
        listener.setCellDataList(cellDataList);
        listener.setColumnNames(Arrays.asList(columnNames));

    }
    @Test
    void rowAndColumnTest() {
        CellData testCellData = new CellData();

        int row = 0, column =0;
        testCellData.setRowAndColumn(row,column);
        assertArrayEquals(new int[]{row,column},testCellData.getRowAndColumn());

        row = 4;
        column =6;
        testCellData.setRowAndColumn(row,column);
        assertArrayEquals(new int[]{row,column},testCellData.getRowAndColumn());

        row = 2;
        column =4;
        testCellData.setRowAndColumn(row,column);
        assertArrayEquals(new int[]{row,column},testCellData.getRowAndColumn());
    }

    @Test
    void valueDataTest() {
        CellData testCellData = new CellData();

        String valueData = "12";
        testCellData.setValueData(valueData);
        assertEquals(valueData,testCellData.getValueData());

        valueData = "15.6";
        testCellData.setValueData(valueData);
        assertEquals(valueData,testCellData.getValueData());

        valueData = "-1.6";
        testCellData.setValueData(valueData);
        assertEquals(valueData,testCellData.getValueData());
    }

    @Test
    void equationDataTest() {
        CellData testCellData = new CellData();

        String equationData = "$A $B -";
        testCellData.setEquationData(equationData);
        assertEquals(equationData,testCellData.getEquationData());

        equationData = "$A $B * sin";
        testCellData.setEquationData(equationData);
        assertEquals(equationData,testCellData.getEquationData());

        equationData = "$A $C /";
        testCellData.setEquationData(equationData);
        assertEquals(equationData,testCellData.getEquationData());
    }

    @Test
    void isCellMatched() {
        CellData testCellData = new CellData();

        int row = 0, column =0;
        testCellData.setRowAndColumn(row,column);
        assertEquals(true,testCellData.isCellMatched(row,column));
        assertEquals(false,testCellData.isCellMatched(row+8,column));

        row = 4;
        column =6;
        testCellData.setRowAndColumn(row,column);
        assertEquals(true,testCellData.isCellMatched(row,column));
        assertEquals(false,testCellData.isCellMatched(row,column+1));

        row = 2;
        String columnName ="$A";
        testCellData.setRowAndColumn(row,1);
        testCellData.setColumnName(columnName);
        assertEquals(true,testCellData.isCellMatched(row,columnName));
        assertEquals(false,testCellData.isCellMatched(row,columnName+"Wrong"));

        row = 0;
        columnName ="$F";
        testCellData.setRowAndColumn(row,1);
        testCellData.setColumnName(columnName);
        assertEquals(true,testCellData.isCellMatched(row,columnName));
        assertEquals(false,testCellData.isCellMatched(row,columnName+"Wrong"));

    }

    @Test
    void someFunctionsTest() {
        CellData testCellData = new CellData();

        ViewStates mode = ViewStates.ValueView;
        testCellData.setViewState(mode);

        testCellData.resetUpdateTimes();
    }

    @Test
    void cellDataContentChanged() {
        SimulateTableAndCellData();
        CellData testCellData = cellDataList.get(0);
        testCellData.setViewState(ViewStates.ValueView);
        testCellData.setRowAndColumn(0,0);

        testCellData.cellDataContentChanged("Wrong");
        assertEquals("Error",testCellData.getValueData());

        String valueData = "5";
        testCellData.cellDataContentChanged(valueData);
        assertEquals(valueData,testCellData.getValueData());
        assertEquals(valueData,testCellData.getEquationData());

        testCellData.setViewState(ViewStates.EquationView);
        testCellData.cellDataContentChanged("Wrong");
        assertEquals("Error",testCellData.getValueData());
        valueData = "5";
        testCellData.cellDataContentChanged(valueData);
        assertEquals(valueData+".0",testCellData.getValueData());
        assertEquals(valueData,testCellData.getEquationData());

    }
}