package SpreadSheetState;

import TableObjects.CellData;
import TableObjects.ViewStates;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

public class SpreadSheetContext  {

    private SpreadSheetViewState returnFunction;
    public SpreadSheetContext(){
        returnFunction = (CellData cell)-> {return cell.getValueData();};
    }

    public void setSpreadSheetState(ViewStates state)
    {
        if(state == ViewStates.ValueView)
            returnFunction = (CellData cell)-> {return cell.getValueData();};
        else
            returnFunction = (CellData cell)-> {return cell.getEquationData();};
    }

    public String getCellContent(CellData cellData)
    {
        return returnFunction.getCellContent(cellData);
    }
}
