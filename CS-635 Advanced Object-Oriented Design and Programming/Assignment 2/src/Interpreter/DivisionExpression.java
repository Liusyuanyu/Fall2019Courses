package Interpreter;

// +, -, *, / , lg (base 2) and sin.

public class DivisionExpression implements Expression
{
    private Expression leftExpression;
    private Expression rightExpression ;
    //Constructor
    public DivisionExpression(Expression leftExpression, Expression rightExpression)
    {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public double interpret()
    {
        return this.leftExpression.interpret() / this.rightExpression.interpret();
    }

}
