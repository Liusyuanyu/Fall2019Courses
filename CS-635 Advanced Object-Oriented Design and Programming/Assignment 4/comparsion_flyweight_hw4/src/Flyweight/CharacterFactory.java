package Flyweight;

import java.util.HashMap;
import java.util.Map;

public class CharacterFactory {
    private static final Map<java.lang.Character, Character> characterMap =new HashMap<>();
    private static CharacterFactory instance;
    private CharacterFactory(){ }

    public static CharacterFactory instance() {
        if (instance == null)
            instance = new CharacterFactory();
        return instance;
    }

    public Character get(char character){
        if (characterMap.containsKey(character))
            return characterMap.get(character);
        else{
            Character newCharacter = new Character(character);
            characterMap.put(character,newCharacter);
            return newCharacter;
        }
    }
}
