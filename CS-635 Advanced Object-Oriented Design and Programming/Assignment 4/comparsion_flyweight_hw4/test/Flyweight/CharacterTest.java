package Flyweight;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    @Test
    void CharacterGetterTest() {
        char testItem = 'A';
        Character testCharacter = new Character(testItem);
        assertEquals(testItem,testCharacter.get());
        assertEquals((int)testItem,testCharacter.getUnicode());

        testItem = '/';
        testCharacter = new Character(testItem);
        assertEquals(testItem,testCharacter.get());
        assertEquals((int)testItem,testCharacter.getUnicode());

        testItem = '©';
        testCharacter = new Character(testItem);
        assertEquals(testItem,testCharacter.get());
        assertEquals((int)testItem,testCharacter.getUnicode());

        testItem = 'c';
        testCharacter = new Character(testItem);
        assertEquals(testItem,testCharacter.get());
        assertEquals((int)testItem,testCharacter.getUnicode());

    }
}