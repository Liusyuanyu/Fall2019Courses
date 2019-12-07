package Flyweight;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FontFactoryTest {
    @Test
    void FontsFactoryFunction() {
        FontFactory fontFactory = FontFactory.instance();

        FontKey testKey = new FontKey("TimesRoman",12, FontStyle.PLAIN);
        Font testPlainFont = new Font("TimesRoman", 12, FontStyle.PLAIN);
        Font returnTestFont = fontFactory.get(testKey);
        assertEquals(testPlainFont.getName(), returnTestFont.getName());
        assertEquals(testPlainFont.getSize(), returnTestFont.getSize());
        assertEquals(testPlainFont.getStyle(), returnTestFont.getStyle());
        testPlainFont = returnTestFont;

        testKey = new FontKey("Courier",24, FontStyle.BOLD);
        Font testBoldFont = new Font("Courier",24, FontStyle.BOLD);
        returnTestFont = fontFactory.get(testKey);
        assertEquals(testBoldFont.getName(), returnTestFont.getName());
        assertEquals(testBoldFont.getSize(), returnTestFont.getSize());
        assertEquals(testBoldFont.getStyle(), returnTestFont.getStyle());
        testBoldFont = returnTestFont;


        testKey = new FontKey("Dialog",10, FontStyle.ITALIC);
        Font testItalicFont = new Font("Dialog",10, FontStyle.ITALIC);
        returnTestFont = fontFactory.get(testKey);
        assertEquals(testItalicFont.getName(), returnTestFont.getName());
        assertEquals(testItalicFont.getSize(), returnTestFont.getSize());
        assertEquals(testItalicFont.getStyle(), returnTestFont.getStyle());
        testItalicFont = returnTestFont;


        testKey = new FontKey("TimesRoman",12, FontStyle.PLAIN);
        returnTestFont = fontFactory.get(testKey);
        assertTrue(testPlainFont == returnTestFont);

        testKey = new FontKey("Courier",24, FontStyle.BOLD);
        returnTestFont = fontFactory.get(testKey);
        assertTrue(testBoldFont == returnTestFont);

        testKey = new FontKey("Dialog",10, FontStyle.ITALIC);
        returnTestFont = fontFactory.get(testKey);
        assertTrue(testItalicFont == returnTestFont);

    }

//    void FontsFactoryFunction() {
//        FontFactory fontFactory = FontFactory.instance();
//
//        FontKey testKey = new FontKey("TimesRoman",12, FontStyle.PLAIN);
//        Font testFont = new Font("TimesRoman", 12, FontStyle.PLAIN);
//        Font returnTestFont = fontFactory.get(testKey);
//        int timeFontHasCode = returnTestFont.hashCode();
//        assertEquals(testFont.getName(), returnTestFont.getName());
//        assertEquals(testFont.getSize(), returnTestFont.getSize());
//        assertEquals(testFont.getStyle(), returnTestFont.getStyle());
//
//        testKey = new FontKey("Courier",24, FontStyle.BOLD);
//        testFont = new Font("Courier",24, FontStyle.BOLD);
//        returnTestFont = fontFactory.get(testKey);
//        int courierFontHasCode = returnTestFont.hashCode();
//        assertEquals(testFont.getName(), returnTestFont.getName());
//        assertEquals(testFont.getSize(), returnTestFont.getSize());
//        assertEquals(testFont.getStyle(), returnTestFont.getStyle());
//
//        testKey = new FontKey("Dialog",10, FontStyle.ITALIC);
//        testFont = new Font("Dialog",10, FontStyle.ITALIC);
//        returnTestFont = fontFactory.get(testKey);
//        int italicFontHasCode = returnTestFont.hashCode();
//        assertEquals(testFont.getName(), returnTestFont.getName());
//        assertEquals(testFont.getSize(), returnTestFont.getSize());
//        assertEquals(testFont.getStyle(), returnTestFont.getStyle());
//
//        testKey = new FontKey("TimesRoman",12, FontStyle.PLAIN);
//        returnTestFont = fontFactory.get(testKey);
//        assertEquals(timeFontHasCode,returnTestFont.hashCode());
//
//        testKey = new FontKey("Courier",24, FontStyle.BOLD);
//        returnTestFont = fontFactory.get(testKey);
//        assertEquals(courierFontHasCode,returnTestFont.hashCode());
//
//        testKey = new FontKey("Dialog",10, FontStyle.ITALIC);
//        returnTestFont = fontFactory.get(testKey);
//        assertEquals(italicFontHasCode,returnTestFont.hashCode());
//
//    }

}