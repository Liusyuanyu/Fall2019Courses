package Listeners;

import Interpreter.PostfixInterpreter;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.*;
import java.util.stream.Collectors;

public class EquationSpreadSheetListener implements TableModelListener
{
    private List<int[]> cellReference;
    private JTable syncValueTable;
    private List<String> columnNames;
    private PostfixInterpreter postfixInterpreter;
    private List<Integer> updatedColumns;

    public EquationSpreadSheetListener ()
    {
        cellReference = new ArrayList<>();
        postfixInterpreter= new PostfixInterpreter();
        updatedColumns= new ArrayList<>();

    }

    @Override
    public void tableChanged(TableModelEvent e) {
        if(e.getType() == TableModelEvent.UPDATE)
        {
            int row = e.getFirstRow();
            int column = e.getColumn();
            TableModel model = (TableModel)e.getSource();
            Object content = model.getValueAt(row, column);
            if(updatedColumns.contains(column)) //The column has already been updated, so ignore updating.
                return;

            EquationTransform equationTransformed = transformEquationAndUpdateDependencey(content,column);
            if(equationTransformed.getIsCorrect())
            {
                try
                {
                    Double output = postfixInterpreter.interpret(equationTransformed.getvalueOnlyEquation());
                    syncValueTable.setValueAt(output,row,column);
                }
                catch (RuntimeException exception)
                {
                    syncValueTable.setValueAt("Error",row,column);
                }
            }
            else
            {
                syncValueTable.setValueAt("Error",row,column);
            }

            updateAllDependencies(column,model);
        }
    }

    public void setSyncTable(JTable table)
    {
        this.syncValueTable = table;
    }
    public void setColumnNames(List<String> columns)
    {
        this.columnNames = columns;
    }

    ////Private methods
    private EquationTransform transformEquationAndUpdateDependencey(Object equation, int column)
    {
        List<String> elements= new ArrayList<>(Arrays.asList((equation.toString()).split(" ")));
        elements.removeIf(element-> element.isEmpty());
        int[] rowAndColumn;
        EquationTransform equationTransform = new EquationTransform();

        //Remove old references
        cellReference.removeIf(reference-> reference[0] == column);
        for (String element:elements)
        {
            if(columnNames.contains(element))
            {
                rowAndColumn = getCoordinateByName(element);
                cellReference.add(new int[]{column,rowAndColumn[1]});
                Object value = syncValueTable.getValueAt(rowAndColumn[0],rowAndColumn[1]);
                equationTransform.append(value.toString());
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
    private Boolean updateAllDependencies(int column, TableModel model)
    {
        AbstractTableModel abstractModel = (AbstractTableModel) model;
        List<int[]> references = cellReference.stream().filter(
                cell -> cell[1] == column).collect(Collectors.toList());
        updatedColumns.add(column);
        for (int[] reference:references)
        {
            abstractModel.fireTableCellUpdated(0,reference[0]);
        }
        updatedColumns.remove((Object)column);
        return true;
    }

    private Boolean isCircular(int parent, List<Integer> referencePath)
    {
        boolean isCorrect = true;
        List<int[]> referenceCells = cellReference.stream().filter(
                cell -> cell[0] == parent).collect(Collectors.toList());
        for (int[] reference:referenceCells)
        {
            if (referencePath.contains(parent)){
                return false;
            }
            else
            {
                referencePath.add(parent);
                isCorrect = isCorrect && isCircular(reference[1],referencePath);
                referencePath.remove((Object)parent);
            }
        }
        return isCorrect;
    }

    private Double getEquationOutput(String ValueOnlyequation)
    {
        Double output =postfixInterpreter.interpret(ValueOnlyequation);
        return  output;
    }
    private int[] getCoordinateByName(String name)
    {
        int[] rowAndColumn = new int[]{0,-1};//Only one row, so the row is always 0.
        rowAndColumn[1] = columnNames.indexOf(name);
        return  rowAndColumn;
    }

}
