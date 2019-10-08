package OperatorStrategy;

import Interpreter.Expression;
import Interpreter.TerminalExpression;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class MultiplicationStrategyTest {

    @Test
    void execute() {
        MultiplicationStrategy testStrategy = new MultiplicationStrategy();
        Stack<Expression> expressionStack = new Stack<>();

        expressionStack.add(new TerminalExpression("10"));

        //Not enough elements(Must more or equal to 2)
        assertThrows(RuntimeException.class, ()->{
            testStrategy.execute(expressionStack);
        });

        expressionStack.add(new TerminalExpression("5.1"));
        Stack<Expression> resultStack = testStrategy.execute(expressionStack);
        assertEquals(51,resultStack.pop().interpret());

        expressionStack.clear();
        expressionStack.add(new TerminalExpression("10"));
        expressionStack.add(new TerminalExpression("-5"));
        resultStack = testStrategy.execute(expressionStack);
        assertEquals(-50,resultStack.pop().interpret());

        expressionStack.clear();
        expressionStack.add(new TerminalExpression("4"));
        expressionStack.add(new TerminalExpression("7"));
        resultStack = testStrategy.execute(expressionStack);
        assertEquals(28,resultStack.pop().interpret());
    }
}