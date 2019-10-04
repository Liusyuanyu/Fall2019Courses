package OperatorStrategy;

import Interpreter.DivisionExpression;
import Interpreter.Expression;

import java.util.Stack;

public class DivisionStrategy implements OperationStrategy
{
    @Override
    public Stack<Expression> execute(Stack<Expression> expressionStack) {
        if(expressionStack.size() < 2)
        {
            throw new RuntimeException("The context isn't postfix.");
        }
        Expression rightExpression = expressionStack.pop();
        Expression leftExpression = expressionStack.pop();
        Expression operationExpression = new DivisionExpression(leftExpression,rightExpression);
        expressionStack.add(operationExpression);
        return expressionStack;
    }
}
