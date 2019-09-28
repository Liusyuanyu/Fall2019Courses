package Listeners;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class SpreadSheetListener implements TableModelListener
{
    @Override
    public void tableChanged(TableModelEvent e) {
//        int row = e.getFirstRow();
//        int column = e.getColumn();
//        TableModel model = (TableModel)e.getSource();
//        String columnName = model.getColumnName(column);
//        Object data = model.getValueAt(row, column);

        if (e.getType() == TableModelEvent.INSERT){
//            System.out.println("Insert");
        }
        else if(e.getType() == TableModelEvent.UPDATE)
        {
//            System.out.println("Update");
        }
        else if(e.getType() == TableModelEvent.DELETE)
        {
//            System.out.println("Delete");
        }
    }
}
