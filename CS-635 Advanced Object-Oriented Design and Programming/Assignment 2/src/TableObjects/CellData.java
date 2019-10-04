package TableObjects;

import Interpreter.PostfixInterpreter;
import Listeners.CellDataListener;
import Listeners.EquationTransform;
import Listeners.TableListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CellData implements CellDataListener
{
    private ViewModes viewMode;
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

    public CellData()
    {
        viewMode = ViewModes.ValueView;
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
    private void addCellDataListener(CellDataListener listener)
    {
        listenerList.add(listener);
    }
    private void removeCellDataListener(CellDataListener listener)
    {
        listenerList.remove(listener);
    }
    public void setTableListener(TableListener tableListener)
    {
        this.tableListener = tableListener;
    }
    public void setRowAndColumn(int row, int column)
    {
        this.row =row;
        this.column =column;
    }
    public int[] getRowAndColumn()
    {
        return new int[]{row,column};
    }
//    public void setValueData(String value)
//    {
//        valueData = value;
//    }
    public String getValueData()
    {
        return valueData;
    }
    public void setColumnName(String name)
    {
        columnName = name;
    }
//    public String getColumnName()
//    {
//        return columnName;
//    }
//    public void setEquationData(String value)
//    {
//        equationData = value;
//    }
    public String getEquationData()
    {
        return equationData;
    }

    public void setViewMode(ViewModes mode)
    {
        viewMode = mode;
    }

    public void resetUpdateTimes()
    {
        updateTimes= 0;
    }

    @Override
    public void cellDataChanged(String newData)
    {
        removeReference();
        if(viewMode == ViewModes.ValueView)
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
            EquationTransform equationTransformed = transformEquationAndUpdateDependency(equationData,true);
            executeEquationProcess(equationTransformed);
        }
        fireCellDataChanged(true);
    }

    @Override
    public void cellDataUpdate()
    {
        if(numberOfReference <= this.updateTimes++)
        {
            return;
        }
        EquationTransform equationTransformed = transformEquationAndUpdateDependency(equationData,false);
        executeEquationProcess(equationTransformed);
        fireCellDataChanged(false);
        if(viewMode == ViewModes.ValueView)
        {
            tableListener.updateValueNoEvent(row,column,valueData);
        }
    }

    public Boolean isCellMatched(int row, int column)
    {
        return (this.row==row)&&(this.column==column);
    }
    public Boolean isCellMatched(int row, String name)
    {
        return (this.row==row)&&(this.columnName.equals(name));
    }

    ////Private
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
    private EquationTransform transformEquationAndUpdateDependency(String equation, Boolean updateReference)
    {
        List<String> elements= new ArrayList<>(Arrays.asList((equation).split(" ")));
        elements.removeIf(String::isEmpty);
        CellData referCell;
        EquationTransform equationTransform = new EquationTransform();

        for (String element:elements)
        {
            if(tableListener.isColumnName(element))
            {
                referCell = tableListener.getCellData(row,element);
                String value = referCell.getValueData();
                equationTransform.append(value);
                if(updateReference)
                {
                    numberOfReference ++;
                    referCell.addCellDataListener(this);
                    referenceCellList.add(referCell);
                }
            }
            else
            {
                equationTransform.append(element);
            }

        }
        ArrayList<Integer> referencePath = new ArrayList<>();
        equationTransform.setIsCorrect(isCircular(column,referencePath));
        return equationTransform;
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
//            throw exception;
        }
        return true;
    }
    private void executeEquationProcess(EquationTransform equationTransformed)
    {
        if(equationTransformed.getIsCorrect())
        {
            try
            {
                String valueOnlyEquation = equationTransformed.getValueOnlyEquation();
                if(valueOnlyEquation.equals(""))//Empty cell. Don't need to update
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
