package AdventureGame;

public class ToolStore extends  NormalLocation{
    private Player player;
    private int select;

    public ToolStore(Player player) {
        super(player);
    }

    @Override
    public boolean onLocation() {
        System.out.println("*****Mağazaya Hoşgeldiniz*****");
        while(true) {
            System.out.println(" ");
            System.out.println("*1*Silah Al\n*2*Zırh Al\n*3*Çıkış Yap");
            setSelect();
            if(select == 1){
                Weapon weapon=Weapon.select();
                System.out.println("Seçilen silah : "+weapon.getName());
                weapon.buy(getPlayer());
            }
            if(select == 2){
                Armor armor= Armor.select();
                System.out.println("Seçilen zırh : "+armor.getName());
                armor.buy(getPlayer());
            }
            if(select == 3){
                System.out.println("Yine Bekleriz .. ");
                break;
            }

        }
        return true;
    }

    public int getSelect() {
        return select;
    }

    public void setSelect() {
        while(true) {
            System.out.print("Yapmak istediğiniz işlem kodu : ");
            select= sca.nextInt();
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
