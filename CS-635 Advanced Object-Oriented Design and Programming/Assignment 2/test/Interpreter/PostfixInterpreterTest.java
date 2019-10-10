package Interpreter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostfixInterpreterTest {


    @Test
    void interpret() {
        PostfixInterpreter postfixInterpreter = new PostfixInterpreter();


        String inputString = "10 5 +";
        assertEquals(15, postfixInterpreter.interpret(inputString));

        inputString = "10 5 -";
        assertEquals(5, postfixInterpreter.interpret(inputString));

        inputString = "10 5 *";
        assertEquals(50, postfixInterpreter.interpret(inputString), 50);

        inputString = "12 2.5 / ";
        assertEquals(12/2.5 , postfixInterpreter.interpret(inputString));

        inputString = "1 2 3 + -";
        assertEquals(-4,postfixInterpreter.interpret(inputString));

        assertThrows(NumberFormatException.class, () -> {
            String errorInput = "ABC";
            postfixInterpreter.interpret(errorInput);
        });

        assertThrows(RuntimeException.class, () -> {
            String errorInput = "1 2 3 /";
            postfixInterpreter.interpret(errorInput);
        });
    }
}