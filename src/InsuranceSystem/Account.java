package InsuranceSystem;

import java.util.ArrayList;

public abstract class Account {
    private User user;
    public Account(User user) {
        this.user = user;
    }
    enum AuthenticationStatus{
        SUCCESS,FAIL;
    }
    abstract void insuranceAdd();
    AuthenticationStatus authenticationStatus = AuthenticationStatus.FAIL;
    private ArrayList<Insurance> insuranceArrayList = new ArrayList<>();

    public final void showUserInfo(){
        System.out.println("Name : " + user.getName());
        System.out.println("Surname : " + user.getSurname());
        System.out.println("Email : " + user.getEmail());
        System.out.println("Password : " + user.getPassword());
        System.out.println("Job : " + user.getJob());
        System.out.println("Age : " + user.getAge());
        System.out.println("Last login date : " + user.getLastLoginDate());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AuthenticationStatus getAuthenticationStatus() {
        return authenticationStatus;
    }

    public void setAuthenticationStatus(AuthenticationStatus authenticationStatus) {
        this.authenticationStatus = authenticationStatus;
    }

    public ArrayList<Insurance> getInsuranceArrayList() {
        return insuranceArrayList;
    }

    public void setInsuranceArrayList(ArrayList<Insurance> insuranceArrayList) {
        this.insuranceArrayList = insuranceArrayList;
    }
}
