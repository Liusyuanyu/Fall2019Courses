package Interpreter;

import static java.lang.Math.*;

public class SineExpression implements Expression
{
    private Expression numberExpression;
    //Constructor
    public SineExpression(Expression numberExpression)
    {
        this.numberExpression= numberExpression;
    }

    @Override
    public double interpret()
    {
        try {
            Double radiant = Math.toRadians(numberExpression.interpret());
            return sin(radiant);
        }
        catch (NumberFormatException inputException)
        {
            throw inputException;
        }
    }

}
