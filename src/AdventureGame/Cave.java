package AdventureGame;
public class Cave extends BattleLocation{
    private Obstacle obstacle=Obstacle.zombie;
    private int piece;
    public Cave(Player player) {super(player);}

    @Override
    public boolean onLocation() {
        System.out.println(" ");
        System.out.println("*****Mağaraya Hoşgeldiniz*****");
        System.out.println("Burda başı boş gezen zombiler var dikkat etmelisiniz ...");
        System.out.println("Oh yo ! İşte orda !");
        sca.nextLine();
        showMonster(obstacle);
        piece=monsterPiece();
        obstacle=monsterOverride(obstacle,piece);
        if(fightYESorNO(obstacle,piece)==true){
            if(fight(obstacle)==true){
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

