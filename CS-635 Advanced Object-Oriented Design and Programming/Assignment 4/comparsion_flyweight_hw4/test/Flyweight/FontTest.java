package Flyweight;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FontTest {

    @Test
    void fontGetters() {
        String name = "Times";
        int size = 12;
        FontStyle style = FontStyle.PLAIN;
        Font testFont = new Font(name,size,style);

        assertTrue(name.compareTo(testFont.getName()) == 0);
        assertEquals(size,testFont.getSize());
        assertEquals(style,testFont.getStyle());
        String descriptor = String.format("Name: %s, Size: %d, Style: %s",name,size,style.name());
        assertTrue(descriptor.compareTo(testFont.toString()) == 0);

        name = "Dialog";
        size = 16;
        style = FontStyle.BOLD;
        testFont = new Font(name,size,style);

        assertTrue(name.compareTo(testFont.getName()) == 0);
        assertEquals(size,testFont.getSize());
        assertEquals(style,testFont.getStyle());
        descriptor = String.format("Name: %s, Size: %d, Style: %s",name,size,style.name());
        assertTrue(descriptor.compareTo(testFont.toString()) == 0);
    }
}