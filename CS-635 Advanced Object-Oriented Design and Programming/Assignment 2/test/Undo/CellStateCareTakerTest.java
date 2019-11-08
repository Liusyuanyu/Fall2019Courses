package Undo;

import TableObjects.CellData;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class CellStateCareTakerTest {

    @Test
    void undoFunctionTest()
    {
        CellStateCareTaker testCellStateCareTaker = new CellStateCareTaker();

        CellStateOriginator testOriginator = new CellStateOriginator();
        CellData testCellData = new CellData();
        testCellData.setEquationData("$A $B -");
        testCellData.setValueData("13");
        testCellData.setRowAndColumn(0,0);
        testOriginator.setCellState(testCellData);

        testCellStateCareTaker.save(testOriginator);

        CellData testCellData2 = new CellData();
        testCellData2.setEquationData("$C $D $A - * + sin");
        testCellData2.setValueData("Error");
        testCellData2.setRowAndColumn(7,3);
        testOriginator.setCellState(testCellData2);

        CellData returnCellData = testOriginator.getCellState();
        assertNotEquals(testCellData.getValueData(),returnCellData.getValueData());
        assertFalse(Arrays.equals(testCellData.getRowAndColumn(),returnCellData.getRowAndColumn()));
        assertNotEquals(testCellData.getEquationData(),returnCellData.getEquationData());

        testCellStateCareTaker.undo(testOriginator);

        returnCellData = testOriginator.getCellState();
        assertEquals(testCellData.getValueData(),returnCellData.getValueData());
        assertArrayEquals(testCellData.getRowAndColumn(),returnCellData.getRowAndColumn());
        assertEquals(testCellData.getEquationData(),returnCellData.getEquationData());

        testCellStateCareTaker.undo(testOriginator);

        returnCellData = testOriginator.getCellState();
        assertNull(returnCellData);
    }
}