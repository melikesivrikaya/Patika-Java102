package AdventureGame;

public class Obstacle {
    private String Name;
    private int ID;
    private int damage;
    private int health;
    private int money;

    public Obstacle(String name, int ID, int damage,int health, int money) {
        Name = name;
        this.ID = ID;
        this.damage = damage;
        this.health=health;
        this.money = money;
    }
    public static Obstacle zombie=new Obstacle("Zombie",1,3,10,4);
    public static Obstacle vampire=new Obstacle("Vampire",2,4,14,7);
    public static Obstacle bear=new Obstacle("Ayı",3,7,22,12);


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
