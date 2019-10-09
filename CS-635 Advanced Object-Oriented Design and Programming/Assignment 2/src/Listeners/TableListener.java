package Listeners;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;
import java.util.Optional;

import SpreadSheetState.SpreadSheetContext;
import TableObjects.CellData;
import TableObjects.ViewStates;
import Undo.CellStateCareTaker;
import Undo.CellStateOriginator;

public class TableListener implements TableModelListener
{
    private List<CellData> cellDataList;
    private List<String> columnNames;
    private TableModel tableModel;
    private Boolean isTableChangedWork;

    private CellStateCareTaker careTaker;
    private CellStateOriginator originator;

    private ViewStates viewState;
    private SpreadSheetContext spreadSheetContext;


    public TableListener()
    {
        isTableChangedWork = true;
        careTaker = new CellStateCareTaker();
        originator = new CellStateOriginator();

        viewState = ViewStates.ValueView;
        spreadSheetContext = new SpreadSheetContext();

    }
    public void setCellDataList(List<CellData> cells)
    {
        cellDataList = cells;
    }
    public void setColumnNames(List<String> columns)
    {
        this.columnNames = columns;
    }

    public CellData getCellData(int row, Object column)
    {
        if(cellDataList ==null)
            return null;
        Optional<CellData> resultList;
        if(column instanceof String)
        {
            resultList = cellDataList.stream().filter(cell->cell.isCellMatched(row,(String)column)).findFirst();
        }
        else if (column instanceof Integer)
        {
            resultList = cellDataList.stream().filter(cell->cell.isCellMatched(row,(Integer)column)).findFirst();
        }
        else
            return null;
        return resultList.orElse(null);
    }
    public Boolean isColumnName(String name)
    {
        return columnNames.contains(name);
    }
    public void setTableModel(TableModel model)
    {
        this.tableModel = model;
    }

    public void updateValueNoEvent(int row, int column, String value)
    {
        isTableChangedWork = false;
        tableModel.setValueAt(value,row,column);
        isTableChangedWork = true;
    }
    public void switchViewState(ViewStates state)
    {
        viewState = state;
        isTableChangedWork = false;
        int[] rowAndColumn;
        spreadSheetContext.setSpreadSheetState(state);
        for(CellData cell : cellDataList)
        {
            cell.setViewState(state);
            rowAndColumn = cell.getRowAndColumn();
            tableModel.setValueAt(spreadSheetContext.getCellContent(cell),rowAndColumn[0],rowAndColumn[1]);
        }
        isTableChangedWork = true;
    }
    public void resetUpdateTimes()
    {
        for(CellData cell : cellDataList)
        {
            cell.resetUpdateTimes();
        }
    }

    //Undo
    private void saveCellState(CellData cell)
    {
        originator.setCellState(cell);
        careTaker.save(originator);
    }
    public void undoCellState()
    {
        careTaker.undo(originator);
        CellData lastState = originator.getCellState();
        if(lastState !=null)
        {
            int[] rowAndColumn = lastState.getRowAndColumn();
            CellData undoTarget = getCellData(rowAndColumn[0],rowAndColumn[1]);
            undoTarget.setEquationData(lastState.getEquationData());
            undoTarget.setValueData(lastState.getValueData());
            tableModel.setValueAt(spreadSheetContext.getCellContent(undoTarget),rowAndColumn[0],rowAndColumn[1]);
            undoTarget.undoEquationUpdate();
        }
    }

    @Override
    public void tableChanged(TableModelEvent e)
    {
        if(!isTableChangedWork)
            return;

        int row = e.getFirstRow();
        int column = e.getColumn();
        Object cellData = tableModel.getValueAt(row, column);
        CellData cell = getCellData(row,column);

        //If the data doesn't change, skip updating.
        if(cell.getValueData().equals(cellData) || cell.getEquationData().equals(cellData) ){
            return;
        }

        //Save old data before update
        saveCellState(cell);
        //Fire contentChanged
        cell.cellDataContentChanged(cellData.toString());
    }
}
