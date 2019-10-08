package OperatorStrategy;

import Interpreter.Expression;
import Interpreter.TerminalExpression;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class AdditionStrategyTest {

    @Test
    void execute() {
        AdditionStrategy testStrategy = new AdditionStrategy();
        Stack<Expression> expressionStack = new Stack<>();

        expressionStack.add(new TerminalExpression("10"));

        //Not enough elements(Must more or equal to 2)
        assertThrows(RuntimeException.class, ()->{
            testStrategy.execute(expressionStack);
        });

        expressionStack.add(new TerminalExpression("10"));
        Stack<Expression> resultStack = testStrategy.execute(expressionStack);
        assertEquals(20,resultStack.pop().interpret());

        expressionStack.clear();
        expressionStack.add(new TerminalExpression("10"));
        expressionStack.add(new TerminalExpression("-5"));
        resultStack = testStrategy.execute(expressionStack);
        assertEquals(5,resultStack.pop().interpret());

        expressionStack.clear();
        expressionStack.add(new TerminalExpression("10"));
        expressionStack.add(new TerminalExpression("2.5"));
        resultStack = testStrategy.execute(expressionStack);
        assertEquals(12.5,resultStack.pop().interpret());
    }
}