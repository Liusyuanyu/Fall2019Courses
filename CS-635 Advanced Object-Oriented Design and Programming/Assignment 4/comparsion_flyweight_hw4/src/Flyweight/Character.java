package Flyweight;

public class Character {
    private int unicode;

    public Character(char character){
        unicode = (int)character;
    }
    public char get(){
        return (char)unicode;
    }
    public int getUnicode(){
        return unicode;
    }
}
