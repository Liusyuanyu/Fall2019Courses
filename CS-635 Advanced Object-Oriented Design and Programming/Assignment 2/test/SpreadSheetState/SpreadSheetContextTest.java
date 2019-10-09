package SpreadSheetState;

import TableObjects.CellData;
import TableObjects.ViewStates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpreadSheetContextTest {

    @Test
    void spreadSheetStateTest()
    {
        SpreadSheetContext testContext = new SpreadSheetContext();
        CellData cellData = new CellData();
        cellData.setValueData("20");
        cellData.setEquationData("$A $B +");

        testContext.setSpreadSheetState(ViewStates.ValueView);
        assertEquals(cellData.getValueData(),testContext.getCellContent(cellData));
        testContext.setSpreadSheetState(ViewStates.EquationView);
        assertEquals(cellData.getEquationData(),testContext.getCellContent(cellData));
    }
}