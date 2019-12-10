package Flyweight;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CharacterFactoryTest {

    @Test
    void CharacterFactoryGetterTest() {
        CharacterFactory charFactory = CharacterFactory.instance();
        int aHashCode =charFactory.get('A').hashCode();
        int cHashCode =charFactory.get('C').hashCode();
        int poundHashCode =charFactory.get('#').hashCode();

        char testItem = 'C';
        Character testCharacter = charFactory.get(testItem);
        assertEquals(testItem,testCharacter.get());
        assertEquals((int)testItem,testCharacter.getUnicode());
        assertEquals(cHashCode,testCharacter.hashCode());

        testItem = '/';
        testCharacter = charFactory.get(testItem);
        assertEquals(testItem,testCharacter.get());
        assertEquals((int)testItem,testCharacter.getUnicode());
        assertNotEquals(cHashCode,testCharacter.hashCode());
        int slashHasCode = testCharacter.hashCode();

        testItem = 'A';
        testCharacter = charFactory.get(testItem);
        assertEquals(testItem,testCharacter.get());
        assertEquals((int)testItem,testCharacter.getUnicode());
        assertEquals(aHashCode,testCharacter.hashCode());

        testItem = '#';
        testCharacter = charFactory.get(testItem);
        assertEquals(testItem,testCharacter.get());
        assertEquals((int)testItem,testCharacter.getUnicode());
        assertEquals(poundHashCode,testCharacter.hashCode());

        testItem = '/';
        testCharacter = charFactory.get(testItem);
        assertEquals(slashHasCode,testCharacter.hashCode());
    }
}