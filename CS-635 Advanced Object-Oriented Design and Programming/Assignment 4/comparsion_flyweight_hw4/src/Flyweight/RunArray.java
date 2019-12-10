package Flyweight;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class RunArray {
    private List<int [] > indexList;
    private List<Font> fontList;
    private int currentLength;

    public RunArray(){
        indexList = new ArrayList<>();
        fontList = new ArrayList<>();
        currentLength = 0;
    }

    public void addRun(int indexStart, int length, Font font){
        int[] range = new int[]{indexStart, indexStart+length-1};
        currentLength += length;
        indexList.add(range);
        fontList.add(font);
    }

    public void appendRun(int length, Font font){
        addRun(currentLength, length,font);
    }

    public Font getFont(int index){
        OptionalInt fontIndex = IntStream.range(0,indexList.size())
                .filter(element -> indexList.get(element)[0]
                        <= index && indexList.get(element)[1] > index)
                .findFirst();
        if(!fontIndex.isPresent())
            return null;
        return fontList.get(fontIndex.getAsInt());
    }
}
