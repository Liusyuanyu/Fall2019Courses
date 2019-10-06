package Undo;

import TableObjects.CellData;

public class CellStateOriginator
{
    private CellData cellState;

    public CellStateOriginator(){}

    public void setCellState(CellData cellState)
    {
        this.cellState = new CellData();
        this.cellState.setValueData(cellState.getValueData());
        this.cellState.setEquationData(cellState.getEquationData());
        int[] rowAndColumn = cellState.getRowAndColumn();
        this.cellState.setRowAndColumn(rowAndColumn[0],rowAndColumn[1]);
    }
    public Memento createMemento()
    {
        return new Memento(cellState);
    }
    public void undoToLastSave(Object memento)
    {
        if(memento==null)
        {
            this.cellState = null;
            return;
        }
        Memento lastSave = (Memento)memento;
        this.cellState = lastSave.getCellState();
    }
    public CellData getCellState()
    {
        return cellState;
    }

    private class Memento
    {
        private CellData cellState;
        private Memento(CellData cellState)
        {
            this.cellState = cellState;
        }
        private CellData getCellState()
        {
            return  cellState;
        }

    }
}
