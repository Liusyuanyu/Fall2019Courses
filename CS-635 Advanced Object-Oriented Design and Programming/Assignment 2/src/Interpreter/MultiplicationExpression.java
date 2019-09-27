package Interpreter;

public class MultiplicationExpression implements Expression
{
    private Expression leftExpression = null;
    private Expression rightExpression = null;
    //Constructor
    public MultiplicationExpression(Expression leftExpression, Expression rightExpression)
    {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public double interpret()
    {
        try {
            return this.leftExpression.interpret() * this.rightExpression.interpret();
        }
        catch (NumberFormatException inputException)
        {
            throw inputException;
        }
    }

}
