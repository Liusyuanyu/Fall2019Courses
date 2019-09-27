package Interpreter;

import OperatorStrategy.OperationContext;

import java.util.List;
import java.util.Stack;

public class PostfixInterpreter {

    private List<String> operators;
    private OperationContext operationStrategy;

    public PostfixInterpreter()
    {
        operationStrategy = new OperationContext();
        operators = operationStrategy.getOperators();
        // +, -, *, / , lg (base 2) and sin.
//        operators = Arrays.asList(new String[]{"+","-","*","/","lg","sin"});
//        elementStack = new Stack<String>();
//        expressionStack = new Stack<Expression>();
//        this.context = context;
//        Stack<String> elementStack = new Stack<String>();
    }

    public Double interpret(String content)
    {
        String[] elements = content.split(" ");

        Stack<Expression> expressionStack = new Stack<>();
        Expression expression;
        for (String element:elements)
        {
            if(operators.contains(element) )//It's a operators
            {
                operationStrategy.setOperation(element);
                expressionStack= operationStrategy.executeOperation(expressionStack);
            }
            else
            {
                expression = new TerminalExpression(element);
                expressionStack.add(expression);
            }
        }
        if (expressionStack.size() != 1)
        {
            throw new RuntimeException("The context isn't postfix.");
        }

        //Return the result of context by postfix.
        expression = expressionStack.pop();
        return expression.interpret();
    }



}
