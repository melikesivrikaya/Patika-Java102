package AdventureGame;
import java.util.Scanner;

public abstract class Location {
    public static Scanner sca = new Scanner(System.in);
    public Location(Player player) {
        this.player = player;
    }
    private Player player;

    public abstract boolean onLocation();





    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
