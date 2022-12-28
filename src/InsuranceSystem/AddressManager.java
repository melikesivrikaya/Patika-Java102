package InsuranceSystem;

import java.util.Scanner;

public class AddressManager{
    public static Scanner sca = new Scanner(System.in);
    public static int ID = 1;
    public static void addressAdd(Account account){
        String city,district,street;
        int no;
        System.out.print("City : ");
        city = sca.nextLine();
        System.out.print("District : ");
        district = sca.nextLine();
        System.out.print("Street : ");
        street = sca.nextLine();
        System.out.print("No : ");
        no = sca.nextInt();
        System.out.print("1 - Home / 2 - Businness : ");
        int select = sca.nextInt();
        sca.nextLine();
        switch (select){
            case 1:
                account.getUser().getAddressArrayList().add(new HomeAddress(ID,city,district,street,no));

                break;
            case 2:
                account.getUser().getAddressArrayList().add(new BusinessAddress(ID,city, district, street, no));
                break;
        }
        System.out.println("Address add.");
        ID++;
    }
    public static void addressDelete(Account account){
        System.out.print("ID : ");
        int id = sca.nextInt();
        if(account.getUser().getAddressArrayList().isEmpty()){
            System.out.println("There is no data in the list");
        }
        else {
            try {
                account.getUser().getAddressArrayList().remove(id-1);
                System.out.println("Address deleted");
            }
            catch (Exception e){
                System.out.println("You did not enter the appropriate ID");
            }
        }
    }
}
