package Interpreter;

import static org.junit.jupiter.api.Assertions.*;

class DivisionExpressionTest {
    @org.junit.jupiter.api.Test
    void interpret() {
        TerminalExpression rightExpression = new TerminalExpression("10");
        TerminalExpression leftExpression = new TerminalExpression("10");

        DivisionExpression testObject = new DivisionExpression(rightExpression,leftExpression);
        assertEquals(testObject.interpret(), 1);

        rightExpression = new TerminalExpression("12");
        leftExpression = new TerminalExpression("-3");
        testObject = new DivisionExpression(rightExpression,leftExpression);

        assertEquals(-4 , testObject.interpret());

        rightExpression = new TerminalExpression("10");
        leftExpression = new TerminalExpression("4");
        testObject = new DivisionExpression(rightExpression,leftExpression);

        assertEquals(2.5, testObject.interpret());
    }
}