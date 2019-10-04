package OperatorStrategy;

import Interpreter.Expression;
import Interpreter.SineExpression;

import java.util.Stack;

public class SineStrategy implements OperationStrategy
{
    @Override
    public Stack<Expression> execute(Stack<Expression> expressionStack) {
        if(expressionStack.size() < 1)
        {
            throw new RuntimeException("The context isn't postfix.");
        }
        Expression expression = expressionStack.pop();
        Expression operationExpression = new SineExpression(expression);
        expressionStack.add(operationExpression);
        return expressionStack;
    }
}
