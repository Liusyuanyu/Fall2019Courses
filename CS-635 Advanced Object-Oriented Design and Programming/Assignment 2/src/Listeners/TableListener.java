package Listeners;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;
import java.util.Optional;

import TableObjects.CellData;
import TableObjects.ViewModes;

public class TableListener implements TableModelListener
{
    private List<CellData> cellDataList;
    private List<String> columnNames;
    private TableModel tableModel;
    private Boolean isTableChangedWord;

    public TableListener()
    {
        isTableChangedWord = true;
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
//    public CellData getCellData(int row, String name)
//    {
//        if(cellDataList ==null)
//            return null;
//        Optional<CellData> resultList = cellDataList.stream().filter(cell->cell.isCellMatched(row,name)).findFirst();
//        return resultList.orElse(null);
//    }
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
        isTableChangedWord = false;
        tableModel.setValueAt(value,row,column);
        isTableChangedWord = true;
    }

    public void switchViewMode(ViewModes mode)
    {
        isTableChangedWord = false;
        int[] rowAndColumn;
        for(CellData cell : cellDataList)
        {
            cell.setViewMode(mode);
            rowAndColumn = cell.getRowAndColumn();
            if(mode==ViewModes.ValueView)
            {
                tableModel.setValueAt(cell.getValueData(),rowAndColumn[0],rowAndColumn[1]);
            }
            else
            {
                tableModel.setValueAt(cell.getEquationData(),rowAndColumn[0],rowAndColumn[1]);
            }
        }
        isTableChangedWord = true;
    }
    public void resetUpdateTimes()
    {
        for(CellData cell : cellDataList)
        {
            cell.resetUpdateTimes();
        }
    }

    @Override
    public void tableChanged(TableModelEvent e)
    {
        if(!isTableChangedWord)
            return;

        int row = e.getFirstRow();
        int column = e.getColumn();
        Object cellData = tableModel.getValueAt(row, column);
        CellData cell = getCellData(row,column);
        cell.cellDataChanged(cellData.toString());
    }
}
