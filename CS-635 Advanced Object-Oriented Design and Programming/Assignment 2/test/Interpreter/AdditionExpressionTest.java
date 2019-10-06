package Interpreter;

import static org.junit.jupiter.api.Assertions.*;

class AdditionExpressionTest {
    @org.junit.jupiter.api.Test
    void interpret() {
        TerminalExpression rightExpression = new TerminalExpression("10");
        TerminalExpression leftExpression = new TerminalExpression("10");

        AdditionExpression testObject = new AdditionExpression(rightExpression,leftExpression);
        assertEquals(testObject.interpret(), 20);

        rightExpression = new TerminalExpression("11.5");
        leftExpression = new TerminalExpression("-3");
        testObject = new AdditionExpression(rightExpression,leftExpression);

        assertEquals(testObject.interpret(), 8.5);
    }
}