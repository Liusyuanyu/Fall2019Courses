package Flyweight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RunArrayTest {

    @Test
    void runArrayAddAndGet() {
        RunArray runArray = new RunArray();
        String normalText="Copyright ©, All rights reserved";
        String boldText="Object-Oriented Design";

        Font testPlainFont= new Font("Time",16, FontStyle.PLAIN);
        runArray.addRun(0,normalText.length(),testPlainFont);

        Font testBoldFont = new Font("Family name",12, FontStyle.BOLD);
        runArray.appendRun(boldText.length(),testBoldFont);

        Font target = runArray.getFont(5);
        assertEquals(testPlainFont,target);

        target = runArray.getFont(normalText.length()+5);
        assertEquals(testBoldFont,target);

        target = runArray.getFont(normalText.length()+boldText.length()+1);
        assertNull(target);

    }
}