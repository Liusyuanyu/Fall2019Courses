package Interpreter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AdditionExpressionTest {
    @Test
    void interpret() {
        TerminalExpression rightExpression = new TerminalExpression("10");
        TerminalExpression leftExpression = new TerminalExpression("10");

        AdditionExpression testObject = new AdditionExpression(rightExpression,leftExpression);
        assertEquals(20, testObject.interpret());

        rightExpression = new TerminalExpression("11.5");
        leftExpression = new TerminalExpression("-3");
        testObject = new AdditionExpression(rightExpression,leftExpression);

        assertEquals(8.5, testObject.interpret());
    }
}