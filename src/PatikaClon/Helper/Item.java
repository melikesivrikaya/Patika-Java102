package PatikaClon.Helper;

public class Item {
    private int key;
    private String values;

    public Item(int key, String values) {
        this.key = key;
        this.values = values;
    }
    public Item(){}

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return getValues();
    }
}
