package Interpreter;

import static org.junit.jupiter.api.Assertions.*;
import static java.lang.Math.log;
import org.junit.jupiter.api.Test;

class LogBaseTwoExpressionTest {
    @Test
    void interpret() {
        TerminalExpression numberExpression = new TerminalExpression("10");

        LogBaseTwoExpression testObject = new LogBaseTwoExpression(numberExpression);
        assertEquals(log(10)/ log(2), testObject.interpret());

        numberExpression = new TerminalExpression("32");
        testObject = new LogBaseTwoExpression(numberExpression);
        assertEquals(log(32)/ log(2), testObject.interpret());

        numberExpression = new TerminalExpression("123");
        testObject = new LogBaseTwoExpression(numberExpression);
        assertEquals(log(123)/ log(2) , testObject.interpret());
    }
}