package Interpreter;

import static org.junit.jupiter.api.Assertions.*;

class TerminalExpressionTest {
    @org.junit.jupiter.api.Test
    void interpretNumeric() {
        TerminalExpression testObject = new TerminalExpression("10");
        assertEquals(testObject.interpret(), 10);

        testObject = new TerminalExpression("-12.3");
        assertEquals(testObject.interpret(), -12.3);

        testObject = new TerminalExpression("92");
        assertEquals(testObject.interpret(), 92);
    }

    @org.junit.jupiter.api.Test
    void interpretNonNumeric() {
        TerminalExpression testObject = new TerminalExpression("Non-Numeric");
        assertThrows(NumberFormatException.class, testObject::interpret);

        testObject = new TerminalExpression("$A");
        assertThrows(NumberFormatException.class, testObject::interpret);
    }
}