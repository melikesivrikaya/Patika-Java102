package AdventureGame;

import java.util.Scanner;

public class Player {
    private String name;
    private int ID;
    private int health;
    private int demage;
    private int money;
    private Inventory inventory;
    public static Scanner sca=new Scanner(System.in);

    public Player(String name,int ID, int health, int demage, int money) {
        this.inventory=new Inventory(false,false,false,0,0);
        this.name = name;
        this.ID=ID;
        this.health = health;
        this.demage = demage;
        this.money = money;
    }
    public static Player selectChar(){
        Player[] selectCharArray={new Player("Samuray",1,21,5,15),
                new Player("Okçu   ",2,18,7,20),
                new Player("Şovalye",3,24,8,5)
        };

        for(Player p : selectCharArray){
            System.out.println("Karakter : "+p.name+"\tID : "+p.ID+"\tSağlık : "+p.health+"\t  Hasar : "+p.demage+"\tPara : "+p.money);
        }

        System.out.print("Seçmek istediğiniz karakter ID : ");
        int charID = sca.nextInt();

        for (Player p : selectCharArray){
            if((p.ID) == charID){
                return p;
            }
        }
        System.out.println("İstenilen aralıkta ID girmediniz, varsayılan karakter atandı.");
        return selectCharArray[0];

    }
    public void showChar(){
        System.out.println("*****************************************************************************************************");
        System.out.println("Karakter : "+name+"\t\tID : "+ID+"\t\tSağlık : "+health+"\t\tHasar : "+demage+"\t\tPara : "+money);
        System.out.println("*****************************************************************************************************");
        System.out.println("Engelleme : "+getInventory().getArmorDefence()
                +"\t\tEk Hasar : "+getInventory().getWeaponDamage()
                +"\t\tOdun : "+getInventory().isFirewoord()
                +"\t\tSu : "+getInventory().isWater()
                +"\t\tYemek : "+getInventory().isFood());
        System.out.println("*****************************************************************************************************");
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    public int getDemage() {
        return demage;
    }

    public void setDemage(int demage) {
        this.demage = demage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
