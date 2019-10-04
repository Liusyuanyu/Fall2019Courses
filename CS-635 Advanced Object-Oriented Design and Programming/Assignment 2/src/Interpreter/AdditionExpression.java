package Interpreter;

public class AdditionExpression implements Expression
{
    private Expression leftExpression;
    private Expression rightExpression;
    //Constructor
    public AdditionExpression(Expression leftExpression,Expression rightExpression)
    {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public double interpret()
    {
        try {
            return this.leftExpression.interpret() + this.rightExpression.interpret();
        }
        catch (NumberFormatException inputException)
        {
            throw inputException;
        }
    }

}
