package AdventureGame;

import java.util.Random;

public class Coal extends BattleLocation{
    private static Obstacle obstacle ;
    private int piece;

    public Coal(Player player) {
        super(player);
        obstacle = new Obstacle("Yılan",4,obstacleDamage(),12,0);

    }
    public static int obstacleDamage(){
        Random r = new Random();
        int low = 3;
        int high = 7;
        int result = r.nextInt(high-low) + low;
        return result;
    }
    @Override
    public boolean onLocation() {
        System.out.println(" ");
        System.out.println("*****Ormana Hoşgeldiniz*****");
        System.out.println("Karanlıklara dikkat etmelisin ...");
        System.out.println("Bişey seni izliyor !");
        sca.nextLine();
        showMonster(obstacle);
        piece = monsterPiece1and5();
        obstacle = monsterOverride(obstacle,piece);
        if(fightYESorNO(obstacle,piece) == true){
            if(fight(obstacle) == true){
                giveLoot();
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
    protected int monsterPiece1and5(){
        return (int)(Math.random() * 5 ) + 1;
    }

    public void giveLoot(){
        double random = (Math.random() * 100 ) + 1;
        int money = 0;
        if(random <= 25){
            if(random <= 12.5 ){
                System.out.println("1 adet para kazandınız .");
                money = 1;
            }
            else if(random <= 20){
                System.out.println("5 adet para kazandınız .");
                money = 5;
            }
            else {
                System.out.println("10 adet para kazandınız .");
                money = 10;
            }
            money += getPlayer().getMoney();
            getPlayer().setMoney(money);

        }
        else if(random <= 40){
            int defence = 0 ;
            if(random <= 32.5 ){
                System.out.println("Hafif zırh kazandınız .");
                defence = 1 ;
            }
            else if(random <= 37){
                System.out.println("Orta zırh kazandınız .");
                defence = 3 ;
            }
            else {
                System.out.println("Ağır zırh para kazandınız .");
                defence = 5 ;
            }
            defence += getPlayer().getInventory().getArmorDefence();
            getPlayer().getInventory().setArmorDefence(defence);
        }
        else if(random <= 55){
            int damage =  0;
            if(random <= 47.5 ){
                System.out.println("Tabanca kazandınız .");
                damage = 2 ;
            }
            else if(random <= 52){
                System.out.println("Kılıç kazandınız .");
                damage = 3 ;
            }
            else {
                System.out.println("Tüfek para kazandınız .");
                damage = 7 ;
            }
            damage += getPlayer().getInventory().getWeaponDamage();
            getPlayer().getInventory().setWeaponDamage(damage);
        }
        else {
            System.out.println("Hiç birşey kazanamadınız .. ");
        }
    }
}
