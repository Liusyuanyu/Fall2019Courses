import javax.swing.*;
import Interpreter.*;

public class SpreadSheetGUI {
    public static void main(String[] args) {
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
}