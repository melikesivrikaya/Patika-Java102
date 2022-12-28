package AdventureGame;

public class SafeHause extends NormalLocation{
    private Player player;

    public SafeHause(Player player) {
        super(player);
    }

    @Override
    public boolean onLocation() {
        if(control() == true){
            System.out.println("*****TEBRİKLER OYUNU KAZANDINNIZ*****");
            Game.win = true;
            return false;
        }
        else{
            System.out.println("*****Güvenli Eve Hoşgeldiniz*****");
            System.out.println("Yılmak Yok Yola Devam ...  :)");
            sca.nextLine();
            getPlayer().setHealth(Game.charHealth);
            System.out.println("Sağlığınız yenilendi.");
        }
        return true;
    }

    public boolean control(){
        if( getPlayer().getInventory().isWater() == false || getPlayer().getInventory().isFood() == false || getPlayer().getInventory().isFirewoord()==false){
            return false;
        }
        return  true;
    }
}
