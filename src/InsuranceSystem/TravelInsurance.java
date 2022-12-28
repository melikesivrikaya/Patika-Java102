package InsuranceSystem;

public class TravelInsurance extends Insurance{
    public TravelInsurance(String insuranceName,  double price, int ID) {
        super(insuranceName, price, ID);
    }
    @Override
    void calculate() {
        setPrice(5000);
    }
}
