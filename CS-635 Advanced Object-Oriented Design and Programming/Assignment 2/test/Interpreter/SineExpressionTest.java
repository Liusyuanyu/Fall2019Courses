package Interpreter;

import static java.lang.Math.sin;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SineExpressionTest {
    @Test
    void interpret() {
        TerminalExpression numberExpression = new TerminalExpression("10");

        SineExpression testObject = new SineExpression(numberExpression);
        double radiant = Math.toRadians(10);
        assertEquals(sin(radiant), testObject.interpret());

        numberExpression = new TerminalExpression("32");
        testObject = new SineExpression(numberExpression);
        radiant = Math.toRadians(32);
        assertEquals(sin(radiant), testObject.interpret());

        numberExpression = new TerminalExpression("123");
        testObject = new SineExpression(numberExpression);
        radiant = Math.toRadians(123);
        assertEquals(sin(radiant), testObject.interpret());

    }
}