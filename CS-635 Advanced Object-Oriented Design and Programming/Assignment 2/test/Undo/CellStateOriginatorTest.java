package Undo;

import TableObjects.CellData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CellStateOriginatorTest {

    @Test
    void cellStateTest() {
        CellStateOriginator testOriginator = new CellStateOriginator();
        CellData testCellData = new CellData();
        testCellData.setEquationData("$A %B -");
        testCellData.setValueData("13");
        testCellData.setRowAndColumn(0,0);
        testOriginator.setCellState(testCellData);

        CellData returnCellData = testOriginator.getCellState();
        assertEquals(testCellData.getValueData(),returnCellData.getValueData());
        assertArrayEquals(testCellData.getRowAndColumn(),returnCellData.getRowAndColumn());
        assertEquals(testCellData.getEquationData(),returnCellData.getEquationData());

        testCellData.setEquationData("$A $B - * sin");
        testCellData.setValueData("Erro");
        testCellData.setRowAndColumn(7,3);
        testOriginator.setCellState(testCellData);

        returnCellData = testOriginator.getCellState();
        assertEquals(testCellData.getValueData(),returnCellData.getValueData());
        assertArrayEquals(testCellData.getRowAndColumn(),returnCellData.getRowAndColumn());
        assertEquals(testCellData.getEquationData(),returnCellData.getEquationData());
    }

    @Test
    void undoFunctionTest() {
        CellStateOriginator testOriginator = new CellStateOriginator();
        CellData testCellData = new CellData();
        testCellData.setEquationData("$A $B -");
        testCellData.setValueData("13");
        testCellData.setRowAndColumn(0,0);
        testOriginator.setCellState(testCellData);

        Object history_1 = testOriginator.createMemento();

        CellData testCellData2 = new CellData();
        testCellData2.setEquationData("$C $D $A - * + sin");
        testCellData2.setValueData("Erro");
        testCellData2.setRowAndColumn(7,3);
        testOriginator.setCellState(testCellData2);

        Object history_2 = testOriginator.createMemento();

        CellData testCellData3 = new CellData();
        testCellData3.setEquationData("$A sin");
        testCellData3.setValueData("2.3");
        testCellData3.setRowAndColumn(2,3);
        testOriginator.setCellState(testCellData3);

        CellData returnCellData = testOriginator.getCellState();
        assertNotEquals(testCellData2.getValueData(),returnCellData.getValueData());
        assertFalse(Arrays.equals(testCellData2.getRowAndColumn(),returnCellData.getRowAndColumn()));
        assertNotEquals(testCellData2.getEquationData(),returnCellData.getEquationData());

        testOriginator.undoToLastSave(history_2);

        returnCellData = testOriginator.getCellState();
        assertEquals(testCellData2.getValueData(),returnCellData.getValueData());
        assertArrayEquals(testCellData2.getRowAndColumn(),returnCellData.getRowAndColumn());
        assertEquals(testCellData2.getEquationData(),returnCellData.getEquationData());

        testOriginator.undoToLastSave(history_1);

        returnCellData = testOriginator.getCellState();
        assertEquals(testCellData.getValueData(),returnCellData.getValueData());
        assertArrayEquals(testCellData.getRowAndColumn(),returnCellData.getRowAndColumn());
        assertEquals(testCellData.getEquationData(),returnCellData.getEquationData());

        testOriginator.undoToLastSave(null);

        returnCellData = testOriginator.getCellState();
        assertNull(returnCellData);
    }
}