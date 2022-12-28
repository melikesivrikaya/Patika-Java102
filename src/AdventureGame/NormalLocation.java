package AdventureGame;

public abstract class NormalLocation extends Location {
    private Player player;
    public NormalLocation(Player player) {
        super(player);
    }
    @Override
    public boolean onLocation() {
        return true;
    }
}
