package AdventureGame;

import java.util.Scanner;
public class Game {
    public static int charHealth;
    public static boolean win=false;

    public static Scanner sca=new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("*****MACERA OYUNUNA HOŞGELDİNİZ*****");
        Player player=Player.selectChar();
        System.out.println("Karakteriniz : "+player.getName());
        charHealth= player.getHealth();

        System.out.print("Kazanmanız gereken 3 eşya var.. Bu eşyaları gittiğiniz yerden savaşarak kazanacaksınız...");
        sca.nextLine();
        System.out.print("--ODUN--  --SU--  --YEMEK--  Bu üçüyle güvenli eve gelebilirseniz oyunu kazandınız demektir ...");
        sca.nextLine();
        System.out.print("BOŞ ŞANS :)");
        sca.nextLine();

        Location location=null;

        while(true){

            System.out.println(" ");
            System.out.println("*1*Güvenli Ev\n*2*Mağaza\n*3*Mağara\n*4*Orman\n*5*Nehir\n*6*Maden\n*7*Bilgileri Göster");
            System.out.print("Gitmek istediğiniz yerin kodunu giriniz : ");
            int select=sca.nextInt();
            switch (select){
                case 1:
                    location = new SafeHause(player);
                    break;
                case 2:
                    location=new ToolStore(player);
                    break;
                case 3:
                    location=new Cave(player);
                    break;
                case 4:
                    location=new Forest(player);
                    break;
                case 5:
                    location=new River(player);
                    break;
                case 6:
                    location=new Coal(player);
                    break;
                case 7:
                    player.showChar();
                    continue;
                default:
                    System.out.println("Geçerli bir değer girmediniz.");
            }
            if(location.onLocation()==false && win ==false){
                System.out.println("*****GAME OVER******");
                break;
            }
            if(win==true){
                break;
            }

        }

}
}
