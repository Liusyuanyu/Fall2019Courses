package Interpreter;

import static java.lang.Math.log;

public class LogBaseTwoExpression implements Expression
{
    private Expression numberExpression;
    //Constructor
    public LogBaseTwoExpression(Expression numberExpression)
    {
        this.numberExpression= numberExpression;
    }

    @Override
    public double interpret()
    {
        return log(numberExpression.interpret()) / log(2);
    }

}
