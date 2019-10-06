package Interpreter;

public class MultiplicationExpression implements Expression
{
    private Expression leftExpression;
    private Expression rightExpression;
    //Constructor
    public MultiplicationExpression(Expression leftExpression, Expression rightExpression)
    {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public double interpret()
    {
        return this.leftExpression.interpret() * this.rightExpression.interpret();
    }

}
