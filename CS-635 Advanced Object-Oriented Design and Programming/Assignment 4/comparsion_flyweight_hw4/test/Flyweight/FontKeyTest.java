package Flyweight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FontKeyTest {

    @Test
    void GetterTest() {
        String testName= "Times";
        int testSize= 12;
        FontStyle testStyle= FontStyle.PLAIN;
        FontKey testKey = new FontKey(testName, testSize, testStyle);
        assertEquals(0, testName.compareTo(testKey.getName()));
        assertEquals(testSize, testKey.getSize());
        assertEquals(testStyle, testKey.getStyle());
    }

    @Test
    void OverrideFunctionsTest() {
        String testName= "Times";
        int testSize= 12;
        FontStyle testStyle= FontStyle.UNDERLINE;
        FontKey testKey = new FontKey(testName, testSize, testStyle);
        int testHashcode = testKey.hashCode();

        FontKey differentKey = new FontKey(testName, testSize+5
                , testStyle);
        assertNotEquals(testHashcode,differentKey.hashCode());

        FontKey compareKey = new FontKey(testName, testSize, testStyle);
        assertEquals(testKey, compareKey);

    }

}