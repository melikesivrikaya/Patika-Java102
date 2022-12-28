package AdventureGame;
public class Weapon implements ToolStoreBuy {
    private String name;
    private int ID;
    private int damage;
    private int money;
    private static int select;

    public Weapon(String name, int ID, int damage, int money) {
        this.name = name;
        this.ID = ID;
        this.damage = damage;
        this.money = money;
    }


    private static Weapon[] weaponArray = { new Weapon("Tabanca",1,2,25),
                                            new Weapon("Kılıç",2,3,35),
                                            new Weapon("Tüfek",3,7,45)};


    private static void show(){
        for(Weapon w: weaponArray){
            System.out.println("Silah : "+w.name+"\tID : "+w.ID+"\tHasar : "+w.damage+"\tPara : "+w.money);
        }
    }
    public  static Weapon select() {
        show();
        selectControl();
        return weaponArray[select-1];
    }

    private static void selectControl(){
        do {
            System.out.println("NOT : Geçerli silah ID si girmelisiniz.");
            System.out.print("Seçmek istediğiniz silah ID : ");
            select=sca.nextInt();
        }while (select <= 0 || select > weaponArray.length);
    }




    @Override
    public void buy(Player player){
        System.out.print(player.getName() + " karakteri için satın alma işlemi gerçekleşiyor...");
        sca.nextLine();
        sca.nextLine();
        if(money > player.getMoney()){
            System.out.print("Yeterli paranız bulunmamaktadır.");
            sca.nextLine();
        }
        else{
            System.out.println("Satın alma işlemi başarılı.");
            int buy = player.getMoney() - money;
            player.setMoney(buy);
            System.out.println("Kalan paranız : "+player.getMoney());
            System.out.println(" ");
            System.out.println("Silahınız envanterinize eklendi.");
            player.getInventory().setWeaponDamage(damage);
            int demage = player.getDemage() + player.getInventory().getWeaponDamage();
            player.setDemage(demage);
            System.out.println(player.getName()+" karakterinin silah hasarı : "+ player.getInventory().getWeaponDamage()+" birim arttı.");
        }

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
    public int getSelect() {
        return select;
    }

    public void setSelect(int select) {
        this.select = select;
    }
}
