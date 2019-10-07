package Interpreter;

import static org.junit.jupiter.api.Assertions.*;

class MultiplicationExpressionTest {
    @org.junit.jupiter.api.Test
    void interpret() {
        TerminalExpression rightExpression = new TerminalExpression("10");
        TerminalExpression leftExpression = new TerminalExpression("10");

        MultiplicationExpression testObject = new MultiplicationExpression(rightExpression,leftExpression);
        assertEquals(100, testObject.interpret());

        rightExpression = new TerminalExpression("12");
        leftExpression = new TerminalExpression("-3");
        testObject = new MultiplicationExpression(rightExpression,leftExpression);

        assertEquals(-36, testObject.interpret());

        rightExpression = new TerminalExpression("2.3");
        leftExpression = new TerminalExpression("4");
        testObject = new MultiplicationExpression(rightExpression,leftExpression);

        assertEquals(9.2, testObject.interpret());
    }

}