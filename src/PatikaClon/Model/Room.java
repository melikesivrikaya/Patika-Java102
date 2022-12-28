package PatikaClon.Model;

public class Room {
    private int id,badPiece ,m2,hotel_id,stok,price;
    private boolean tv,minibar,console,till,projection;
    private String type;

    public Room(int id, int badPiece, int m2, int hotel_id, int stok, int price, boolean tv, boolean minibar, boolean console, boolean till, boolean projection, String type) {
        this.id = id;
        this.badPiece = badPiece;
        this.m2 = m2;
        this.hotel_id = hotel_id;
        this.stok = stok;
        this.price = price;
        this.tv = tv;
        this.minibar = minibar;
        this.console = console;
        this.till = till;
        this.projection = projection;
        this.type = type;
    }
    public Room(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBadPiece() {
        return badPiece;
    }

    public void setBadPiece(int badPiece) {
        this.badPiece = badPiece;
    }

    public int getM2() {
        return m2;
    }

    public void setM2(int m2) {
        this.m2 = m2;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public boolean isMinibar() {
        return minibar;
    }

    public void setMinibar(boolean minibar) {
        this.minibar = minibar;
    }

    public boolean isConsole() {
        return console;
    }

    public void setConsole(boolean console) {
        this.console = console;
    }

    public boolean isTill() {
        return till;
    }

    public void setTill(boolean till) {
        this.till = till;
    }

    public boolean isProjection() {
        return projection;
    }

    public void setProjection(boolean projection) {
        this.projection = projection;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
