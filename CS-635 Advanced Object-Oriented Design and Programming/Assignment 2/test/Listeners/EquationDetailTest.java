package Listeners;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquationDetailTest {

    @Test
    void equationAppendGetTest() {
        EquationDetail testEquationDetail = new EquationDetail();
        String finalString = "10";

        testEquationDetail.append("10");
        assertEquals(finalString, testEquationDetail.getValueOnlyEquation());

        finalString += " 30";
        testEquationDetail.append("30");
        assertEquals(finalString, testEquationDetail.getValueOnlyEquation());

        finalString += " -";
        testEquationDetail.append("-");
        assertEquals(finalString, testEquationDetail.getValueOnlyEquation());
    }

    @Test
    void isCorrectTeste() {
        EquationDetail testEquationDetail = new EquationDetail();
        testEquationDetail.setIsCorrect(true);
        assertTrue(testEquationDetail.getIsCorrect());
        testEquationDetail.setIsCorrect(false);
        assertFalse(testEquationDetail.getIsCorrect());
    }
}