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
//    ArrayList<Map<Integer,Integer>> cellReference;
//    Map<Integer,Integer> cellReference;
    List<int[]> cellReference;
    JTable syncValueTable;
    List<String> columns;
    PostfixInterpreter postfixInterpreter;
    List<Integer> updatedColumns;

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
//            System.out.println("tableChanged: Event triggered");
            int row = e.getFirstRow();
            int column = e.getColumn();
            TableModel model = (TableModel)e.getSource();
            Object content = model.getValueAt(row, column);
//            Double output = getEquationOutput(content);
//            Double output = getEquationOutput(getValueOnlyEquationAndUpdateReference(content,column));
            if(updatedColumns.contains(column)) //The column has already been updated, so ignore updating.
                return;

            if(verifyAndUpdateReference(content,column))
            {
                Double output = getEquationOutput(getValueOnlyEquation(content));
                syncValueTable.setValueAt(output,row,column);
            }
            else
            {
                syncValueTable.setValueAt("Error",row,column);
            }

            updateAllDependencies(column,model);
        }
    }

    public boolean setSyncTable(JTable table)
    {
        this.syncValueTable = table;
        return true;
    }
    public boolean setColumnNames(List<String> columns)
    {
        this.columns = columns;
        return true;
    }

    ////Private methods
    private String getValueOnlyEquationAndUpdateReference_old(Object equation, int column)
    {
        List<String> elements= new ArrayList<>(Arrays.asList((equation.toString()).split(" ")));
        elements.removeIf(element-> element.isEmpty());
        StringBuilder valueOnlyEquation = new StringBuilder();
        int[] rowAndColumn;

        //Remove old references
        cellReference.removeIf(reference-> reference[0] == column);

        for (String element:elements)
        {
            if(columns.contains(element))
            {
                rowAndColumn = getCoordinateByName(element);
                Object value = syncValueTable.getValueAt(rowAndColumn[0],rowAndColumn[1]);
                valueOnlyEquation.append(value.toString().replace(" ",""));
                valueOnlyEquation.append(" ");

                cellReference.add(new int[]{column,rowAndColumn[1]});
            }
            else
            {
                valueOnlyEquation.append(element.toString().replace(" ",""));
                valueOnlyEquation.append(" ");
            }
        }
        String postfixEquation = valueOnlyEquation.toString();
        return  postfixEquation;
    }
    private String getValueOnlyEquation(Object equation)
    {
        List<String> elements= new ArrayList<>(Arrays.asList((equation.toString()).split(" ")));
        elements.removeIf(element-> element.isEmpty());
        StringBuilder valueOnlyEquation = new StringBuilder();
        int[] rowAndColumn;

        for (String element:elements)
        {
            if(columns.contains(element))
            {
                rowAndColumn = getCoordinateByName(element);
                Object value = syncValueTable.getValueAt(rowAndColumn[0],rowAndColumn[1]);
                valueOnlyEquation.append(value.toString().replace(" ",""));
                valueOnlyEquation.append(" ");
            }
            else
            {
                valueOnlyEquation.append(element.toString().replace(" ",""));
                valueOnlyEquation.append(" ");
            }
        }
        String postfixEquation = valueOnlyEquation.toString();
        return  postfixEquation;
    }
    private Boolean verifyAndUpdateReference(Object equation, int column)
    {
        List<String> elements= new ArrayList<>(Arrays.asList((equation.toString()).split(" ")));
        elements.removeIf(element-> element.isEmpty());
        StringBuilder valueOnlyEquation = new StringBuilder();
        int[] rowAndColumn;
        Boolean isCorrect = true;

        //Remove old references
        cellReference.removeIf(reference-> reference[0] == column);
        for (String element:elements)
        {
            if(columns.contains(element))
            {
                rowAndColumn = getCoordinateByName(element);
                Object value = syncValueTable.getValueAt(rowAndColumn[0],rowAndColumn[1]);
                cellReference.add(new int[]{column,rowAndColumn[1]});
            }
            else
            {
                valueOnlyEquation.append(element.toString().replace(" ",""));
            }
        }
        ArrayList<Integer> referencePath = new ArrayList<>();
        isCorrect = isCircular(column,referencePath);
        return isCorrect;
    }
    private Boolean updateAllDependencies(int column, TableModel model)
    {
        AbstractTableModel abstractModel = (AbstractTableModel) model;
        List<int[]> references = cellReference.stream().filter(
                cell -> cell[1] == column).collect(Collectors.toList());
//        System.out.println("updateAllDependencies: Start Update");
        updatedColumns.add(column);
        for (int[] reference:references)
        {
//            updatedColumns.add(reference[0]);
            abstractModel.fireTableCellUpdated(0,reference[0]);
        }
        updatedColumns.remove((Object)column);
//        System.out.println("updateAllDependencies: Stop Update");
//        updatedColumns.clear();
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
//               if(isCircular(reference[1],referencePath)){
//                   return false;
//               }
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
    private Double getEquationOutput(Object equation)
    {
        List<String> elements= new ArrayList<>(Arrays.asList((equation.toString()).split(" ")));
        elements.removeIf(element-> element.isEmpty());
        StringBuilder valueOnlyEquation = new StringBuilder();
        int[] rowAndColumn;
        for (String element:elements)
        {
            if(columns.contains(element))
            {
                rowAndColumn = getCoordinateByName(element);
                Object value = syncValueTable.getValueAt(rowAndColumn[0],rowAndColumn[1]);
                valueOnlyEquation.append(value.toString().replace(" ",""));
                valueOnlyEquation.append(" ");
            }
            else
            {
                valueOnlyEquation.append(element.toString().replace(" ",""));
                valueOnlyEquation.append(" ");
            }
        }
        String postfixEquation = valueOnlyEquation.toString();
        Double output =postfixInterpreter.interpret(postfixEquation);
        return  output;
    }
    private int[] getCoordinateByName(String name)
    {
        int[] rowAndColumn = new int[]{0,-1};//Only one row, so the row is always 0.
        rowAndColumn[1] = columns.indexOf(name);
        return  rowAndColumn;
    }










//    public void tableChanged(TableModelEvent e) {
////        int row = e.getFirstRow();
////        int column = e.getColumn();
////        TableModel model = (TableModel)e.getSource();
////        String columnName = model.getColumnName(column);
////        Object data = model.getValueAt(row, column);
//
//        if (e.getType() == TableModelEvent.INSERT){
////            System.out.println("Insert");
//        }
//        else if(e.getType() == TableModelEvent.UPDATE)
//        {
////            System.out.println("Update");
//        }
//        else if(e.getType() == TableModelEvent.DELETE)
//        {
////            System.out.println("Delete");
//        }
//    }
}
