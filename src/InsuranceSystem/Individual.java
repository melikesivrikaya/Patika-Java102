package InsuranceSystem;

import java.util.Scanner;

public class Individual extends Account{
    public Individual(User user) {
        super(user);
    }
    private int ID = 0;
    private int year = 1;
    @Override
    void insuranceAdd() {
        Scanner sca= new Scanner(System.in);
        System.out.println("-1- Healt Insurance \n-2- Car Insurance \n-3- Residence Insurance\n-4- Travel Insurance");
        try {
            Insurance insurance = null;
            System.out.print("Select : ");
            int select = sca.nextInt();
            switch (select){
                case 1:
                    insurance = new HealthInsurance("Healt",0,ID);
                    year = 1;
                    break;
                case 2:
                    insurance = new CarInsurance("Car",0,ID);
                    year = 2;
                    break;
                case 3:
                    insurance = new ResidenceInsurance("Residence",0,ID);
                    year = 3;
                    break;
                case 4:
                    insurance = new TravelInsurance("Travel",0,ID);
                    year = 4;
                    break;
                default:
                    System.out.println("Eror select !");
            }
            if(insurance != null){
                insurance.setEndDate(year);
                getInsuranceArrayList().add(insurance);
                getInsuranceArrayList().get(ID).calculate();
                getInsuranceArrayList().get(ID).setPrice(getInsuranceArrayList().get(ID).getPrice() + 200);
                ID++;
            }
        }
        catch (Exception e){
            System.out.println("Eror input");
        }
    }


}
