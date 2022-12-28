package InsuranceSystem;

public class ResidenceInsurance extends Insurance{

    public ResidenceInsurance(String insuranceName,  double price, int ID) {
        super(insuranceName,  price, ID);
    }
    @Override
    void calculate() {
        setPrice(4000);
    }
}
