package InsuranceSystem;

public class CarInsurance extends Insurance{
    public CarInsurance(String insuranceName, double price, int ID) {
        super(insuranceName, price, ID);
    }

    @Override
    void calculate() {
        setPrice(2000);
    }
}
