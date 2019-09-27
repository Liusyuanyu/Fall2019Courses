package OperatorStrategy;

import Interpreter.Expression;

import java.util.Stack;

public interface OperationStrategy
{
    public Stack<Expression> execute(Stack<Expression> expressionStack);
}
