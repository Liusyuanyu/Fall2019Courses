package OperatorStrategy;

import Interpreter.Expression;
import Interpreter.TerminalExpression;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class SineStrategyTest {

    @Test
    void execute() {
        SineStrategy testStrategy = new SineStrategy();
        Stack<Expression> expressionStack = new Stack<>();

        //Not enough elements(Must more or equal to 1)
        assertThrows(RuntimeException.class, ()->{
            testStrategy.execute(expressionStack);
        });

        expressionStack.add(new TerminalExpression("90"));
        Stack<Expression> resultStack = testStrategy.execute(expressionStack);
        Double radians = Math.toRadians(90);
        assertEquals(Math.sin(radians),resultStack.pop().interpret());

        expressionStack.clear();
        expressionStack.add(new TerminalExpression("14.6"));
        resultStack = testStrategy.execute(expressionStack);
        radians = Math.toRadians(14.6);
        assertEquals(Math.sin(radians),resultStack.pop().interpret());
    }
}