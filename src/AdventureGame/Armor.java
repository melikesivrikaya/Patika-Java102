package AdventureGame;

public class Armor implements ToolStoreBuy {
    private String name;
    private int ID;
    private int defence;
    private int money;
    private static int select;
    public Armor(String name, int ID, int defence, int money) {
        this.name = name;
        this.ID = ID;
        this.defence = defence;
        this.money = money;
    }

    public Armor() {

    }

    private static Armor[] armorArray={new Armor("Hafif",1,1,15),
            new Armor("Orta",2,3,25),
            new Armor("Ağır",3,5,40)};

    public static void show(){
        for(Armor a: armorArray) {
            System.out.println("Seviye  : " + a.name + "\tID : " + a.ID + "\tEngelleme : " + a.defence + "\tPara : " + a.money);
        }
    }

    public static Armor select() {
        show();
        selectControl();
        return armorArray[select-1];
    }
    private static void selectControl(){
        do {
            System.out.println("Geçerli zırh ID si girmelisiniz.");
            System.out.print("Seçmek istediğiniz zırh ID : ");
            select=sca.nextInt();
        }while (select <= 0 || select > armorArray.length);
    }





    @Override
    public void buy(Player player){
        System.out.print(player.getName()+" karakteri için satın alma işlemi gerçekleşiyor...");
        sca.nextLine();
        sca.nextLine();
        if(money > player.getMoney()){
            System.out.println("Yeterli paranız bulunmamaktadır.");
        }
        else{
            System.out.println("Satın alma işlemi başarılı.");
            int buy= player.getMoney() - money;
            player.setMoney(buy);
            System.out.println("Kalan paranız : "+player.getMoney());
            System.out.println(" ");
            System.out.println("Zırhınız envanterinize eklendi.");
            player.getInventory().setArmorDefence(defence);
            System.out.println(player.getName()+" karakterinin hasar engelleme özelliği : "+ player.getInventory().getArmorDefence()+" olarak eklendi.");
            sca.nextLine();
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
        return defence;
    }

    public void setDamage(int defence) {
        this.defence = defence;
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
        while(true) {
            if(select == 1 || select == 2 || select == 3){
                this.select = select;
                break;
            }
            else {
                System.out.println("Sunulan değerlerden birini giriniz.");
            }
        }
    }
}
