package OperatorStrategy;

import Interpreter.Expression;
import Interpreter.TerminalExpression;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class DivisionStrategyTest {
    @Test
    void execute() {
        DivisionStrategy testStrategy = new DivisionStrategy();
        Stack<Expression> expressionStack = new Stack<>();

        expressionStack.add(new TerminalExpression("10"));

        //Not enough elements(Must more or equal to 2)
        assertThrows(RuntimeException.class, ()->{
            testStrategy.execute(expressionStack);
        });

        expressionStack.add(new TerminalExpression("10"));
        Stack<Expression> resultStack = testStrategy.execute(expressionStack);
        assertEquals(1,resultStack.pop().interpret());

        expressionStack.clear();
        expressionStack.add(new TerminalExpression("10"));
        expressionStack.add(new TerminalExpression("-5"));
        resultStack = testStrategy.execute(expressionStack);
        assertEquals(-2,resultStack.pop().interpret());

        expressionStack.clear();
        expressionStack.add(new TerminalExpression("10"));
        expressionStack.add(new TerminalExpression("2.5"));
        resultStack = testStrategy.execute(expressionStack);
        assertEquals(4,resultStack.pop().interpret());
    }
}