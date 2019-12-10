package Flyweight;
import java.util.HashMap;
import java.util.Map;

public class FontFactory {
    private static final Map<FontKey, Font> fontMap =new HashMap<>();
    private static FontFactory instance;

    private FontFactory(){}

    public static FontFactory instance() {
        if (instance == null)
            instance = new FontFactory();
        return instance;
    }

    public Font get(FontKey key ){
        if (fontMap.containsKey(key))
            return fontMap.get(key);
        else{
            Font font = new Font(key.getName(),key.getSize(),key.getStyle());
            fontMap.put(key,font);
            return font;
        }
    }
}
