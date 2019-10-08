package TableObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellDataTest {

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
}