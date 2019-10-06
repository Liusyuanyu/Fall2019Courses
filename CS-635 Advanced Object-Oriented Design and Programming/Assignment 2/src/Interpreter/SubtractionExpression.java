package Interpreter;

public class SubtractionExpression implements Expression
{
    private Expression leftExpression;
    private Expression rightExpression;
    //Constructor
    public SubtractionExpression(Expression leftExpression, Expression rightExpression)
    {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public double interpret()
    {
        return this.leftExpression.interpret() - this.rightExpression.interpret();
    }

}
