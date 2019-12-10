package Flyweight;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FontTest {

    @Test
    void fontGettersTest() {
        String name = "Times";
        int size = 12;
        FontStyle style = FontStyle.PLAIN;
        Font testFont = new Font(name,size,style);

        assertEquals(0, name.compareTo(testFont.getName()));
        assertEquals(size,testFont.getSize());
        assertEquals(style,testFont.getStyle());
        String descriptor = String.format("Name: %s, Size: %d, Style: %s",name,size,style.name());
        assertEquals(0, descriptor.compareTo(testFont.toString()));

        name = "Dialog";
        size = 16;
        style = FontStyle.BOLD;
        testFont = new Font(name,size,style);

        assertEquals(0, name.compareTo(testFont.getName()));
        assertEquals(size,testFont.getSize());
        assertEquals(style,testFont.getStyle());
        descriptor = String.format("Name: %s, Size: %d, Style: %s",name,size,style.name());
        assertEquals(0, descriptor.compareTo(testFont.toString()));
    }
}