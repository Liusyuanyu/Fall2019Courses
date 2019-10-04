package OperatorStrategy;

import Interpreter.Expression;
import Interpreter.MultiplicationExpression;

import java.util.Stack;

public class MultiplicationStrategy implements OperationStrategy
{
    @Override
    public Stack<Expression> execute(Stack<Expression> expressionStack) {
        if(expressionStack.size() < 2)
        {
            throw new RuntimeException("The context isn't postfix.");
        }
        Expression rightExpression = expressionStack.pop();
        Expression leftExpression = expressionStack.pop();
        Expression operationExpression = new MultiplicationExpression(leftExpression,rightExpression);
        expressionStack.add(operationExpression);
        return expressionStack;
    }
}
