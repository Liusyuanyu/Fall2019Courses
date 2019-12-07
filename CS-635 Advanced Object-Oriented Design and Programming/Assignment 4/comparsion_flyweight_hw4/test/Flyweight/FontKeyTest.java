package Flyweight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FontKeyTest {

    @Test
    void Getter() {
        String testName= "Times";
        int testSize= 12;
        FontStyle testStyle= FontStyle.PLAIN;
        FontKey testKey = new FontKey(testName, testSize, testStyle);
        assertTrue(testName.compareTo(testKey.getName())==0);
        assertEquals(testSize, testKey.getSize());
        assertEquals(testStyle, testKey.getStyle());
    }

    @Test
    void OverrideFunctions() {
        String testName= "Times";
        int testSize= 12;
        FontStyle testStyle= FontStyle.UNDERLINE;
        FontKey testKey = new FontKey(testName, testSize, testStyle);
        int testHashcode = testKey.hashCode();

        FontKey differentKey = new FontKey(testName, testSize+5, testStyle);
        assertNotEquals(testHashcode,differentKey.hashCode());

        FontKey compareKey = new FontKey(testName, testSize, testStyle);
        assertTrue(testKey.equals(compareKey));

    }

}