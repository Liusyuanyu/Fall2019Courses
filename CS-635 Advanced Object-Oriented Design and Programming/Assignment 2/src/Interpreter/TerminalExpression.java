package Interpreter;
import java.lang.NumberFormatException;

public class TerminalExpression implements Expression{
    private String context;
    //Constructor
    public TerminalExpression(String context)
    {
        this.context =context;
    }

    public double interpret()
    {
        Object[] result;
        result = isNumeric(context);
        if ( (Boolean)(result[0]))
            return (Double) (result[1]);
        throw new NumberFormatException("It not a number.");
    }

    private Object[] isNumeric(String context) {
        Object[] result= new Object[]{true,0};
        try {
            result[1] = Double.parseDouble(context);
        }
        catch (NumberFormatException | NullPointerException nfe) {
            result[0] = false;
        }
        return result;
    }
}
