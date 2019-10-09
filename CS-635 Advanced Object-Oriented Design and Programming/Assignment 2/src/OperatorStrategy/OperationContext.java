package OperatorStrategy;

import Interpreter.*;

import java.util.*;

public class OperationContext
{
    private OperationStrategy strategy;
    private Map<String,OperationStrategy> operationMap;
    public OperationContext()
    {
        this.operationMap = new HashMap<>();
        operationMap.put("+",(Stack<Expression> expressionStack)->{
            if(expressionStack.size() < 2)
            {
                throw new RuntimeException("The context isn't postfix.");
            }
            Expression rightExpression = expressionStack.pop();
            Expression leftExpression = expressionStack.pop();
            Expression operationExpression = new AdditionExpression(leftExpression,rightExpression);
            expressionStack.add(operationExpression);
            return expressionStack;
             });
        operationMap.put("-",(Stack<Expression> expressionStack)->{
            if(expressionStack.size() < 2)
            {
                throw new RuntimeException("The context isn't postfix.");
            }
            Expression rightExpression = expressionStack.pop();
            Expression leftExpression = expressionStack.pop();
            Expression operationExpression = new SubtractionExpression(leftExpression,rightExpression);
            expressionStack.add(operationExpression);
            return expressionStack;
        });
        operationMap.put("*",(Stack<Expression> expressionStack)->{
            if(expressionStack.size() < 2)
            {
                throw new RuntimeException("The context isn't postfix.");
            }
            Expression rightExpression = expressionStack.pop();
            Expression leftExpression = expressionStack.pop();
            Expression operationExpression = new MultiplicationExpression(leftExpression,rightExpression);
            expressionStack.add(operationExpression);
            return expressionStack;
        });
        operationMap.put("/",(Stack<Expression> expressionStack)->{
            if(expressionStack.size() < 2)
            {
                throw new RuntimeException("The context isn't postfix.");
            }
            Expression rightExpression = expressionStack.pop();
            Expression leftExpression = expressionStack.pop();
            Expression operationExpression = new DivisionExpression(leftExpression,rightExpression);
            expressionStack.add(operationExpression);
            return expressionStack;
        });
        operationMap.put("lg",(Stack<Expression> expressionStack)->{
            if(expressionStack.size() < 1)
            {
                throw new RuntimeException("The context isn't postfix.");
            }
            Expression expression = expressionStack.pop();
            Expression operationExpression = new LogBaseTwoExpression(expression);
            expressionStack.add(operationExpression);
            return expressionStack;
        });
        operationMap.put("sin",(Stack<Expression> expressionStack)->{
            if(expressionStack.size() < 1)
            {
                throw new RuntimeException("The context isn't postfix.");
            }
            Expression expression = expressionStack.pop();
            Expression operationExpression = new SineExpression(expression);
            expressionStack.add(operationExpression);
            return expressionStack;
        });
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
