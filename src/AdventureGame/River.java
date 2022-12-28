package AdventureGame;
public class River extends BattleLocation{
    private Player player;
    private Obstacle obstacle = Obstacle.bear;
    private int piece;
    public River(Player player) {
        super(player);
    }

    @Override
    public boolean onLocation() {
        System.out.println(" ");
        System.out.println("*****Nehire Hoşgeldiniz*****");
        System.out.println("Nehirde yüzmek için etrafı çok iyi kontrol etmelisin ...");
        System.out.println("Umarım yanında bal yoktur ..!");
        sca.nextLine();
        showMonster(obstacle);
        piece=monsterPiece();
        obstacle=monsterOverride(obstacle,piece);
        if(fightYESorNO(obstacle,piece) == true){
            if(fight(obstacle) == true){
                giveLoot(obstacle,piece);
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return true;
        }
    }
}
