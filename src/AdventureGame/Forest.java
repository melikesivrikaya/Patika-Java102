package AdventureGame;

public class Forest extends BattleLocation{
    private Obstacle obstacle = Obstacle.vampire;
    private int piece;
    public Forest(Player player) {
        super(player);
    }

    @Override
    public boolean onLocation() {
        System.out.println(" ");
        System.out.println("*****Ormana Hoşgeldiniz*****");
        System.out.println("Karanlıklara dikkat etmelisin ...");
        System.out.println("Bişey seni izliyor !");
        sca.nextLine();
        showMonster(obstacle);
        piece=monsterPiece();
        obstacle = monsterOverride(obstacle,piece);
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
