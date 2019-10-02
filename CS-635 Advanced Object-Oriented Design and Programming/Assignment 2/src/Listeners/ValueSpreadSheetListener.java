package Listeners;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class ValueSpreadSheetListener implements TableModelListener
{
    JTable syncTable;
    public ValueSpreadSheetListener(){}

    @Override
    public void tableChanged(TableModelEvent e) {
        if (syncTable==null)
            return;

        if(e.getType() == TableModelEvent.UPDATE)
        {
            int row = e.getFirstRow();
            int column = e.getColumn();
            TableModel model = (TableModel)e.getSource();
            Object cellData = model.getValueAt(row, column);
            Double cellValue = 0.0;

            try {
                if (cellData.equals("Error") || cellData.equals(""))
                {
//                    System.out.println(cellData);
                    return;
                }
                cellValue = isNumeric(cellData);
            }
            catch (NumberFormatException exception)
            {
                model.setValueAt("Error",row,column);
            }
            return;
        }
    }

    public boolean setSyncTable(JTable table)
    {
        this.syncTable = table;
        return true;
    }

    private Double isNumeric(Object data)
    {
//        Object result= new Object[]{true,0};
        try
        {
            if (data instanceof Double){
                return (Double)data;
            }

            Double result = Double.parseDouble((String) data);
            return result;
        }
        catch (NumberFormatException exception) {
            throw exception;
        }
    }
}
