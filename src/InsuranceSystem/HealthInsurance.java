package InsuranceSystem;

public class HealthInsurance extends Insurance{
    public HealthInsurance(String insuranceName, double price, int ID) {
        super(insuranceName, price, ID);
    }
    @Override
    void calculate() {
        setPrice(3000);
    }
}
