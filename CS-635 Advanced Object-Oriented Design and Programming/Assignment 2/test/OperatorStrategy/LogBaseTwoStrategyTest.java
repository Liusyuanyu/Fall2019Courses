package OperatorStrategy;

import Interpreter.Expression;
import Interpreter.TerminalExpression;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class LogBaseTwoStrategyTest {

    @Test
    void execute() {
        LogBaseTwoStrategy testStrategy = new LogBaseTwoStrategy();
        Stack<Expression> expressionStack = new Stack<>();

        //Not enough elements(Must more or equal to 1)
        assertThrows(RuntimeException.class, ()->{
            testStrategy.execute(expressionStack);
        });

        expressionStack.add(new TerminalExpression("5"));
        Stack<Expression> resultStack = testStrategy.execute(expressionStack);
        assertEquals(Math.log(5)/Math.log(2),resultStack.pop().interpret());

        expressionStack.clear();
        expressionStack.add(new TerminalExpression("10"));
        resultStack = testStrategy.execute(expressionStack);
        assertEquals(Math.log(10)/Math.log(2),resultStack.pop().interpret());

    }
}