package InsuranceSystem;

import java.time.LocalDate;

public abstract class Insurance {
    private String insuranceName;
    private LocalDate startDate,endDate;
    private double price;
    private int ID;
    abstract void calculate();

    public Insurance(String insuranceName ,double price, int ID) {
        this.insuranceName = insuranceName;
        this.price = price;
        this.ID = ID;
        this.setStartDate();
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStartDate() {
        return startDate.toString();
    }

    public void setStartDate() {
        this.startDate = LocalDate.now();
    }

    public String getEndDate() {
        return endDate.toString();
    }

    public void setEndDate(int year) {
        this.endDate = LocalDate.now().plusYears(year);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
