package Interpreter;

// +, -, *, / , lg (base 2) and sin.

public class SubtractionExpression implements Expression
{
    private Expression leftExpression = null;
    private Expression rightExpression = null;
    //Constructor
    public SubtractionExpression(Expression leftExpression, Expression rightExpression)
    {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public double interpret()
    {
        try {
            return this.leftExpression.interpret() - this.rightExpression.interpret();
        }
        catch (NumberFormatException inputException)
        {
            throw inputException;
        }
    }

}
