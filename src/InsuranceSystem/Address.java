package InsuranceSystem;

import java.util.Scanner;

public class Address {

    private String city,district,street;
    private int ID,no;

    public Address(int ID,String city, String district, String street, int no) {
        this.ID = ID;
        this.city = city;
        this.district = district;
        this.street = street;
        this.no = no;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
