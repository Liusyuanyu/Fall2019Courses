package Flyweight;

public class Font {
    private String name;
    private int size;
    private FontStyle style;

    public Font(String name, int size,FontStyle style){
        this.name=name;
        this.size=size;
        this.style=style;
    }
    public String getName(){
        return name;
    }
    public int getSize(){
        return size;
    }
    public FontStyle getStyle(){
        return style;
    }

    public  String toString(){
        return String.format("Name: %s, Size: %d, Style: %s",name,size,style.name());
    }
}
