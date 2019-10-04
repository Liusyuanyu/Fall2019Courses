package OperatorStrategy;

import Interpreter.Expression;

import java.util.Stack;

public interface OperationStrategy
{
    Stack<Expression> execute(Stack<Expression> expressionStack);
}
