import Flyweight.*;
import Flyweight.Character;
import Flyweight.RunArray;

public class Main {

    public static void main(String[] args) {

        String boldText = "CS 635 Advanced Object-Oriented Design & Programming \n" +
                "Fall Semester, 2018 \n" +
                "Doc 17 Mediator, Flyweight, Facade, Demeter, Active Object \n" +
                "Nov 19, 2019";


        String normalText = "Copyright ©, All rights reserved. 2019 SDSU & Roger Whitney, 5500 Campanile Drive, San \n" +
                "Diego, CA 92182-7700 USA. OpenContent (http://www.opencontent.org/opl.shtml) license de-\n" +
                "fines the copyright on this document.\n";

        //Using flyweight
        CharacterFactory charFactory = CharacterFactory.instance();
        FontFactory fontFactory = FontFactory.instance();
        Character[] charArray = new Character[boldText.length()+normalText.length()];
        int index = 0;
        RunArray runArray = new RunArray();

        for(char character : boldText.toCharArray()){
            charArray[index] = charFactory.get(character);
            index++;
        }
        FontKey fontKey = new FontKey("Arial",12, FontStyle.BOLD.BOLD);
        runArray.addRun(0, boldText.length(), fontFactory.get(fontKey));

        for(char character : normalText.toCharArray()){
            charArray[index] = charFactory.get(character);
            index++;
        }
        fontKey = new FontKey("TimesRoman",24, FontStyle.PLAIN);
        runArray.appendRun(normalText.length(), fontFactory.get(fontKey));

        index = 5;
        Font target = runArray.getFont(index);
        System.out.println(String.format("%c(%d) Font: [%s]", (char)(charArray[index].getUnicode()),index,target.toString() ));
        index = 200;
        target = runArray.getFont(index);
        System.out.println(String.format("%c(%d) Font: [%s]", (char)(charArray[index].getUnicode()),index,target.toString() ));


        //Without flyweight
        char[] withoutCharArray = new char[boldText.length()+normalText.length()];
        Font[] withoutFontArray = new Font[withoutCharArray.length];
        index = 0;

        for(char character : boldText.toCharArray()){
            withoutCharArray[index] = character;
            withoutFontArray[index] = new Font("Arial",12,FontStyle.BOLD);
            index++;
        }

        for(char character : normalText.toCharArray()){
            withoutCharArray[index] = character;
            withoutFontArray[index] = new Font("TimesRoman",24,FontStyle.PLAIN);
            index++;
        }
        index=0;
        System.out.println(String.format("%c(%d) Font: [%s]", withoutCharArray[index],index,withoutFontArray[index].toString() ));
        index=200;
        System.out.println(String.format("%c(%d) Font: [%s]", withoutCharArray[index],index,withoutFontArray[index].toString() ));
    }
}
