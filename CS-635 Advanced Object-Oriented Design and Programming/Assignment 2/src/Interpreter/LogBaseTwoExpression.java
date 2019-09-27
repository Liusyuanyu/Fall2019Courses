package Interpreter;

import static java.lang.Math.log;

public class LogBaseTwoExpression implements Expression
{
    private Expression numberExpression = null;
    //Constructor
    public LogBaseTwoExpression(Expression numberExpression)
    {
        this.numberExpression= numberExpression;
    }

    @Override
    public double interpret()
    {
        try {
            return log(numberExpression.interpret()) / log(2);
        }
        catch (NumberFormatException inputException)
        {
            throw inputException;
        }
    }

}
