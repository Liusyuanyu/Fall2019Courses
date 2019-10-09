package TableObjects;

import Interpreter.PostfixInterpreter;
import Listeners.CellDataListener;
import Listeners.EquationDetail;
import Listeners.TableListener;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CellData implements CellDataListener
{
    private ViewStates viewState;
    private int row;
    private int column;
    private String columnName;
    private List<CellDataListener> listenerList;
    private List<CellData> referenceCellList;
    private String valueData;
    private String equationData;
    private TableListener tableListener;
    private PostfixInterpreter postfixInterpreter;
    private int numberOfReference;
    private int updateTimes;

    //Constructor
    public CellData()
    {
        viewState = ViewStates.ValueView;
        row = -1;
        column = -1;
        listenerList = new ArrayList<>();
        referenceCellList = new ArrayList<>();

        columnName="";
        valueData="";
        equationData="";

        numberOfReference=0;
        updateTimes=0;

        postfixInterpreter = new PostfixInterpreter();
    }
    public void setTableListener(TableListener tableListener) { this.tableListener = tableListener; }
    public void setRowAndColumn(int row, int column)
    {
        this.row =row;
        this.column =column;
    }
    public int[] getRowAndColumn() { return new int[]{row,column}; }
    public void setValueData(String value) { valueData = value; }
    public String getValueData() { return valueData; }
    public void setEquationData(String equation) { equationData = equation; }
    public String getEquationData(){ return equationData; }

    public void setColumnName(String name) { columnName = name; }
    public void setViewState(ViewStates state)
    {
        viewState = state;
    }

    public void resetUpdateTimes() { updateTimes= 0; }
    public Boolean isCellMatched(int row, int column)
    {
        return (this.row==row)&&(this.column==column);
    }
    public Boolean isCellMatched(int row, String name)
    {
        return (this.row==row)&&(this.columnName.equals(name));
    }

    public void cellDataContentChanged(String newData)
    {
        removeReference();
        if(!newData.isEmpty())
        {
            if(viewState == ViewStates.ValueView)
            {
                if(isNumeric(newData))
                {
                    valueData = newData;
                }
                else
                {
                    newData = "Error";
                    valueData = newData;
                    tableListener.updateValueNoEvent(row,column,valueData);
                }
                equationData = newData;
            }
            else//EquationView
            {
                equationData = newData;
                EquationDetail equationTransformed = transformEquationAndUpdateDependency(equationData,true);
                executeEquationInterpret(equationTransformed);
            }
        }
        else
        {
            valueData = newData;
            equationData = newData;
        }
        fireCellDataChanged(true);
    }

    public void undoEquationUpdate()
    {
        removeReference();
        EquationDetail equationTransformed = transformEquationAndUpdateDependency(equationData,true);
        executeEquationInterpret(equationTransformed);
        fireCellDataChanged(true);
    }

    @Override
    public void cellDataUpdate()
    {
        if(numberOfReference <= this.updateTimes++)
        {
            return;
        }
        EquationDetail equationTransformed = transformEquationAndUpdateDependency(equationData,false);
        executeEquationInterpret(equationTransformed);
        fireCellDataChanged(false);
        if(viewState == ViewStates.ValueView)
        {
            tableListener.updateValueNoEvent(row,column,valueData);
        }
    }

    ////Private
    private void addCellDataListener(CellDataListener listener) { listenerList.add(listener); }
    private void removeCellDataListener(CellDataListener listener) { listenerList.remove(listener); }
    private void fireCellDataChanged(Boolean isInitiator)
    {
        if(isInitiator)
        {
            tableListener.resetUpdateTimes();
        }

        for(CellDataListener listener:listenerList)
        {
            listener.cellDataUpdate();
        }
    }
    private void removeReference()
    {
        for(CellData cellData : referenceCellList)
        {
            cellData.removeCellDataListener(this);
        }
        referenceCellList.clear();
        numberOfReference = 0;
    }
    private EquationDetail transformEquationAndUpdateDependency(String equation, Boolean updateReference)
    {
        StringTokenizer elements = new StringTokenizer(equation);
        CellData referCell;
        EquationDetail equationDetail = new EquationDetail();

        String element;
        while (elements.hasMoreElements()) {
            element = elements.nextToken();
            if(tableListener.isColumnName(element))
            {
                referCell = tableListener.getCellData(row,element);
                String value = referCell.getValueData();
                //If the value is Empty, let it be zero.
                if (value.isEmpty()){ value="0"; }

                equationDetail.append(value);
                if(updateReference)
                {
                    numberOfReference ++;
                    referCell.addCellDataListener(this);
                    referenceCellList.add(referCell);
                }
            }
            else
            {
                equationDetail.append(element);
            }
        }
        ArrayList<Integer> referencePath = new ArrayList<>();
        equationDetail.setIsCorrect(isCircular(column,referencePath));
        return equationDetail;
    }
    private Boolean isCircular(int parent, List<Integer> referenceColumnPath)
    {
        boolean isCorrect = true;
        int[] rowAndColumn;
        for(CellData cell : referenceCellList)
        {
            rowAndColumn =cell.getRowAndColumn();
            if (referenceColumnPath.contains(rowAndColumn[1]))
            {
                return false;
            }
            else
            {
                referenceColumnPath.add(parent);
                isCorrect = isCorrect && cell.isCircular(rowAndColumn[1],referenceColumnPath);
                referenceColumnPath.remove(Integer.valueOf(parent));
            }
        }
        return isCorrect;
    }
    private Boolean isNumeric(String data)
    {
        try
        {
            Double.parseDouble(data);
        }
        catch (NumberFormatException exception) {
            return false;
        }
        return true;
    }
    private void executeEquationInterpret(EquationDetail equationTransformed)
    {
        if(equationTransformed.getIsCorrect())
        {
            try
            {
                String valueOnlyEquation = equationTransformed.getValueOnlyEquation();
                if(valueOnlyEquation.isEmpty())//Empty cell. Don't need to update
                {
                    return;
                }
                Double output = postfixInterpreter.interpret(valueOnlyEquation);
                valueData = output.toString();
            }
            catch (RuntimeException exception)
            {
                valueData = "Error";
            }
        }
        else
        {
            valueData = "Error";
        }
    }
}
