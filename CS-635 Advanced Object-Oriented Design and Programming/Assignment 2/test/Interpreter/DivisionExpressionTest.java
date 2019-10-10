package Interpreter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DivisionExpressionTest {
    @Test
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