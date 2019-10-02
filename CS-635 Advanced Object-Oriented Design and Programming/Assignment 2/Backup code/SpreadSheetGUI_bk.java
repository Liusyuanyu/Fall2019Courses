import javax.swing.*;
import javax.swing.table.TableModel;

import Interpreter.*;

public class SpreadSheetGUI {
    public static void main_old(){
//        JFrame f=new JFrame();//creating instance of JFrame
//
//        JButton b=new JButton("click");//creating instance of JButton
//        b.setBounds(130,100,100, 40);//x axis, y axis, width, height
//
//        f.add(b);//adding button in JFrame
//
//        f.setSize(400,500);//400 width and 500 height
//        f.setLayout(null);//using no layout managers
//        f.setVisible(true);//making the frame visible

//        TerminalExpression numberOne = new TerminalExpression("10");
//        TerminalExpression numberTwo = new TerminalExpression("-5");
//        AdditionExpression add_operator = new AdditionExpression(numberOne,numberTwo);
//        SubtractionExpression sub_operator = new SubtractionExpression(numberOne,numberTwo);
//        System.out.println("The result of interpreter.");
//        System.out.println(add_operator.interpret());
//        System.out.println(sub_operator.interpret());
//
//        sub_operator = new SubtractionExpression(add_operator,numberTwo);
//        System.out.println(sub_operator.interpret());
//
//        SineExpression sine_operator = new SineExpression(numberOne);
//        System.out.println(sine_operator.interpret());
//
//        LogBaseTwoExpression log_operator = new LogBaseTwoExpression(numberOne);
//        System.out.println(log_operator.interpret());

        PostfixInterpreter interpreter = new PostfixInterpreter();
        String inputString = "10 5 -";
//        String inputString = "1967 21 + 3 sin *";
//        String inputString = "1967 21 + 3 sin * 8 lg *";
        System.out.println(interpreter.interpret(inputString));
    }

    public static void main(String[] args)
    {
        String[] columnNames = {"$A", "$B", "$C", "$D", "$E", "$F", "$G", "$H", "$I"};
        Object[][] data = {{" "," "," "," "," "," "," ", " "," "}};
        JTable table = new JTable(data, columnNames);
        TableModel tableModel = table.getModel();
//        DefaultTableModel tableModel = new DefaultTableModel();
//        JTable table = new JTable(tableModel);
//        tableModel.addColumn(columnNames);
//        tableModel.addRow(data);
        table.setFillsViewportHeight(true);
//        Listeners.ValueSpreadSheetListener



        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVisible(true);
        JFrame frame=new JFrame();//creating instance of JFrame
        frame.setTitle("SpreadSheet");
        frame.add(scrollPane);//adding button in JFrame
        frame.setSize(600,90);//400 width and 500 height
        frame.setLocation(100,200);
        frame.setVisible(true);//making the frame visible
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}