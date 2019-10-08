package Interpreter;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SubtractionExpressionTest {
    @Test
    void interpret() {
        TerminalExpression rightExpression = new TerminalExpression("10");
        TerminalExpression leftExpression = new TerminalExpression("10");

        SubtractionExpression testObject = new SubtractionExpression(rightExpression,leftExpression);
        assertEquals(0, testObject.interpret());

        rightExpression = new TerminalExpression("12");
        leftExpression = new TerminalExpression("-3");
        testObject = new SubtractionExpression(rightExpression,leftExpression);
        assertEquals(15, testObject.interpret());

        rightExpression = new TerminalExpression("6.7");
        leftExpression = new TerminalExpression("4");
        testObject = new SubtractionExpression(rightExpression,leftExpression);
        assertEquals(2.7, testObject.interpret());

        rightExpression = new TerminalExpression("2.3");
        leftExpression = new TerminalExpression("4");
        testObject = new SubtractionExpression(rightExpression,leftExpression);
        assertEquals(2.3 - 4, testObject.interpret());
    }

}