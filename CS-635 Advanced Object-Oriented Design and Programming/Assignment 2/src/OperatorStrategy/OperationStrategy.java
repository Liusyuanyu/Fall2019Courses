package OperatorStrategy;

import Interpreter.Expression;

import java.util.Stack;

interface OperationStrategy
{
    Stack<Expression> execute(Stack<Expression> expressionStack);
}
