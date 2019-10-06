package OperatorStrategy;

import Interpreter.Expression;

import java.util.*;

public class OperationContext
{
    private OperationStrategy strategy;
    private Map<String,OperationStrategy> operationMap;
    public OperationContext()
    {
        this.operationMap = new HashMap<>();

        operationMap.put("+",new AdditionStrategy());
        operationMap.put("-",new SubtractionStrategy());
        operationMap.put("*",new MultiplicationStrategy());
        operationMap.put("/",new DivisionStrategy());
        operationMap.put("lg",new LogBaseTwoStrategy());
        operationMap.put("sin",new SineStrategy());
    }

    public List<String> getOperators()
    {
        return new ArrayList<>(operationMap.keySet());
    }

    public void setOperation(String operator)
    {
        if(!operationMap.containsKey(operator))
        {
            throw new UnsupportedOperationException();
        }
        this.strategy = operationMap.get(operator);
    }

    public Stack<Expression> executeOperation(Stack<Expression> expressionStack)
    {
        expressionStack = this.strategy.execute(expressionStack);
        return expressionStack;
    }
}
