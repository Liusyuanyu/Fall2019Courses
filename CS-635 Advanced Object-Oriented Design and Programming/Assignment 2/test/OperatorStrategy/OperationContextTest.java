package OperatorStrategy;

import Interpreter.Expression;
import Interpreter.TerminalExpression;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import static org.junit.jupiter.api.Assertions.*;

class OperationContextTest {

    @Test
    void getOperators() {
        OperationContext testOperation = new OperationContext();
        List<String> operatorList = Arrays.asList("sin", "*", "+", "lg", "-", "/");
        assertEquals(operatorList,testOperation.getOperators());
    }

    @Test
    void executeAdditionOperation() {
        OperationContext testOperation = new OperationContext();
        Stack<Expression> expressionStack = new Stack<>();

        expressionStack.add(new TerminalExpression("10"));

        testOperation.setOperation("+");
        assertThrows(RuntimeException.class, ()-> testOperation.executeOperation(expressionStack));
        expressionStack.add(new TerminalExpression("10"));
        expressionStack.add(new TerminalExpression("2.5"));
        Stack<Expression> resultStack = testOperation.executeOperation(expressionStack);
        assertEquals(12.5,resultStack.pop().interpret());
    }

    @Test
    void executeSubtractionOperation() {
        OperationContext testOperation = new OperationContext();
        Stack<Expression> expressionStack = new Stack<>();

        expressionStack.add(new TerminalExpression("10"));

        testOperation.setOperation("-");
        assertThrows(RuntimeException.class, ()-> testOperation.executeOperation(expressionStack));
        expressionStack.add(new TerminalExpression("10"));
        expressionStack.add(new TerminalExpression("2.5"));
        Stack<Expression> resultStack = testOperation.executeOperation(expressionStack);
        assertEquals(10-2.5,resultStack.pop().interpret());
    }

    @Test
    void executeMultiplicationOperation() {
        OperationContext testOperation = new OperationContext();
        Stack<Expression> expressionStack = new Stack<>();

        expressionStack.add(new TerminalExpression("10"));

        testOperation.setOperation("*");
        assertThrows(RuntimeException.class, ()-> testOperation.executeOperation(expressionStack));
        expressionStack.add(new TerminalExpression("10"));
        expressionStack.add(new TerminalExpression("2.5"));
        Stack<Expression> resultStack = testOperation.executeOperation(expressionStack);
        assertEquals(10*2.5,resultStack.pop().interpret());
    }

    @Test
    void executeDivisionOperation() {
        OperationContext testOperation = new OperationContext();
        Stack<Expression> expressionStack = new Stack<>();

        expressionStack.add(new TerminalExpression("10"));

        testOperation.setOperation("/");
        assertThrows(RuntimeException.class, ()-> testOperation.executeOperation(expressionStack));
        expressionStack.add(new TerminalExpression("10"));
        expressionStack.add(new TerminalExpression("2.5"));
        Stack<Expression> resultStack = testOperation.executeOperation(expressionStack);
        assertEquals(10/2.5,resultStack.pop().interpret());
    }

    @Test
    void executeSineOperation() {
        OperationContext testOperation = new OperationContext();
        Stack<Expression> expressionStack = new Stack<>();

        testOperation.setOperation("sin");
        assertThrows(RuntimeException.class, ()-> testOperation.executeOperation(expressionStack));
        expressionStack.add(new TerminalExpression("90"));
        Stack<Expression> resultStack = testOperation.executeOperation(expressionStack);
        assertEquals(Math.sin(Math.toRadians(90)),resultStack.pop().interpret());
    }

    @Test
    void executeLogOperation() {
        OperationContext testOperation = new OperationContext();
        Stack<Expression> expressionStack = new Stack<>();

        testOperation.setOperation("lg");
        assertThrows(RuntimeException.class, ()-> testOperation.executeOperation(expressionStack));
        expressionStack.add(new TerminalExpression("20"));
        Stack<Expression> resultStack = testOperation.executeOperation(expressionStack);
        assertEquals(Math.log(20)/Math.log(2),resultStack.pop().interpret());
    }

    @Test
    void errorSetOperation() {
        OperationContext testOperation = new OperationContext();
        assertThrows(UnsupportedOperationException.class, ()-> testOperation.setOperation("Wrong"));
    }
}