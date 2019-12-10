package Flyweight;

public class FontKey {

    private final String name;
    private final int size;
    private final FontStyle style;

    public FontKey(String name, int size, FontStyle style) {
        this.name = name;
        this.size = size;
        this.style = style;
    }

    public String getName() { return name; }
    public int getSize() { return size; }
    public FontStyle getStyle() { return style; }


    @Override
    public int hashCode() {
        final int prime = 37;
        int result = 1;
        result = prime * result + (name != null ? name.hashCode() : 0);
        result = prime * result + size;
        result = prime * result + style.ordinal();
        return result;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        FontKey key = (FontKey)object;
        return (name.equals(key.getName()))&(size == key.getSize())&(style==key.getStyle());
    }
}


