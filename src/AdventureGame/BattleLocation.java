package AdventureGame;

public class BattleLocation extends Location{
    private Player player;
    public BattleLocation(Player player) {
        super(player);
    }
    @Override
    public boolean onLocation() {
        return false;
    }


    //Rastgele Canavar sayısı üretir 1-3 arası
    protected  int monsterPiece(){
        return (int)(Math.random()*3)+1;
    }
    //Üretilen canavar sayısıyla canavarın sağlığını çarpar
    protected Obstacle monsterOverride(Obstacle obstacle, int piece){
        piece=obstacle.getHealth()*piece;
        obstacle.setHealth(piece);
        return obstacle;
    }
    //Canavar bilgileri gösterir
    protected void showMonster(Obstacle obstacle){
        System.out.println("Canavar : "+obstacle.getName()
                +" Hasar : "+obstacle.getDamage()
                +" Sağlık : " +obstacle.getHealth()
                +" Para : "+obstacle.getMoney());
    }
    //Karakterin karşısına çıkan canavarla savaşıp savaşmayacağını sorar
    protected boolean fightYESorNO(Obstacle obstacle,int piece){
        System.out.println("Karşınızda "+piece+" adet "+obstacle.getName()+" var.");
        System.out.print("Savaşmak istiyor musunuz : ");
        String select=sca.nextLine();

        if(select.equals("evet")){
            System.out.print("Bol şans ... !");
            return true;
        }else if(select.equals("hayır")){
            System.out.println("Bazen kaçmak en mantıklı olanı ...");
            return false;
        }else{
            System.out.println("Geçerli bir cevap girilmediğinden cevap 'hayır' olarak alındı.");
            return false;
        }
    }
    protected boolean fight(Obstacle obstacle){
        System.out.println(" ");

        while (true){
            sca.nextLine();
            System.out.println("Bir darbe vurdunuz ...");
            int zombieHealth = obstacle.getHealth() - getPlayer().getDemage();
            obstacle.setHealth(zombieHealth);
            if(obstacle.getHealth() <= 0){
                return true;
            }

            System.out.println( obstacle.getName() +" canavarının sağlığı : " + obstacle.getHealth());
            sca.nextLine();
            System.out.println("Karşı taraftan hasar alındı ...");
            int playerHealth = getPlayer().getHealth() - (obstacle.getDamage() - getPlayer().getInventory().getArmorDefence());
            getPlayer().setHealth(playerHealth);
            if(getPlayer().getHealth() <= 0){
                return false;
            }
            System.out.println("Sağlığınız : " + getPlayer().getHealth());

        }
    }
    //Karaktere ganimeti teslim eder
    protected void giveLoot(Obstacle obstacle,int piece){
        System.out.println("Tebrikler kazandınız ....");
        System.out.println("** " + obstacle.getName() + " paraları sizin oldu **");
        int money = obstacle.getMoney() * piece;
        money = money + getPlayer().getMoney();
        getPlayer().setMoney(money);
        if (obstacle.getID() == 1){
            System.out.println("Bu savaştan birde YEMEK kazandınız **");
            getPlayer().getInventory().setFood(true);
        }
        if (obstacle.getID() == 2){
            System.out.println("Bu savaştan birde ODUN kazandınız **");
            getPlayer().getInventory().setFirewoord(true);
        }
        if (obstacle.getID() == 3){
            System.out.println("Bu savaştan birde SU kazandınız **");
            getPlayer().getInventory().setWater(true);
        }
        sca.nextLine();


    }
}
