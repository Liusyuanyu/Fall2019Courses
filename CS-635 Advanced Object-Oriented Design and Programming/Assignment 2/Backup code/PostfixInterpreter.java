package Interpreter;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PostfixInterpreter {

//    private Stack<String> elementStack;
//    private String context;
//    private Stack<Expression> expressionStack;

    private List<String> operators;

    public PostfixInterpreter()
    {
        // +, -, *, / , lg (base 2) and sin.
        operators = Arrays.asList(new String[]{"+","-","*","/","lg","sin"});
//        elementStack = new Stack<String>();
//        expressionStack = new Stack<Expression>();
//        this.context = context;
//        Stack<String> elementStack = new Stack<String>();
    }

    public Double interpret(String content)
    {
        String[] elements = content.split(" ");

        Stack<Expression> expressionStack = new Stack<Expression>();
        TerminalExpression terminalElement;
        Expression operatorExpression;
        Expression leftExpression;
        Expression rightExpression;
        for (String element:elements)
        {
            if(operators.contains(element) )//It's a operators
            {
                switch (element)
                {
                    case "+":
                        if(expressionStack.size() < 2)
                        {
                            throw new RuntimeException("The context isn't postfix.");
                        }
                        rightExpression = expressionStack.pop();
                        leftExpression = expressionStack.pop();
                        operatorExpression = new AdditionExpression(leftExpression,rightExpression);
                        expressionStack.add(operatorExpression);
                        break;
                    case "-":
                        if(expressionStack.size() < 2)
                        {
                            throw new RuntimeException("The context isn't postfix.");
                        }
                        rightExpression = expressionStack.pop();
                        leftExpression = expressionStack.pop();
                        operatorExpression = new SubtractionExpression(leftExpression,rightExpression);
                        expressionStack.add(operatorExpression);
                        break;

                    case "*":
                        if(expressionStack.size() < 2)
                        {
                            throw new RuntimeException("The context isn't postfix.");
                        }
                        rightExpression = expressionStack.pop();
                        leftExpression = expressionStack.pop();
                        operatorExpression = new MultiplicationExpression(leftExpression,rightExpression);
                        expressionStack.add(operatorExpression);
                        break;
                    case "/":
                        if(expressionStack.size() < 2)
                        {
                            throw new RuntimeException("The context isn't postfix.");
                        }
                        rightExpression = expressionStack.pop();
                        leftExpression = expressionStack.pop();
                        operatorExpression = new DivisionExpression(leftExpression,rightExpression);
                        expressionStack.add(operatorExpression);
                        break;
                    case "lg":
                        if(expressionStack.size() < 1)
                        {
                            throw new RuntimeException("The context isn't postfix.");
                        }
                        leftExpression = expressionStack.pop();
                        operatorExpression = new LogBaseTwoExpression(leftExpression);
                        expressionStack.add(operatorExpression);
                        break;
                    case "sin":
                        if(expressionStack.size() < 1)
                        {
                            throw new RuntimeException("The context isn't postfix.");
                        }
                        leftExpression = expressionStack.pop();
                        operatorExpression = new SineExpression(leftExpression);
                        expressionStack.add(operatorExpression);
                        break;
                }
            }
            else
            {
                terminalElement = new TerminalExpression(element);
                expressionStack.add(terminalElement);
            }
        }

        if (expressionStack.size() != 1)
        {
            throw new RuntimeException("The context isn't postfix.");
        }

        //Return the result of context by postfix.
        operatorExpression = expressionStack.pop();
        return operatorExpression.interpret();
    }



}
