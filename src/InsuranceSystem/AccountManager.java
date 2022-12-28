package InsuranceSystem;

import java.util.TreeSet;

public class AccountManager {
    private static TreeSet<Account> accountTreeSet = new TreeSet<>(new OrderByEmailComparator());

    public static Account login(String email,String password){
        Account account = null;
            for(Account a : accountTreeSet){
                if(a.getUser().getEmail().equals(email) && a.getUser().getPassword().equals(password)){
                    a.setAuthenticationStatus(Account.AuthenticationStatus.SUCCESS);
                    account = a ;
                    account.getUser().setLastLoginDate();
                    System.out.println("Welcome :)");
                }
            }
        if(account == null){
            throw new NullPointerException();
        }
        return account;
    }

}
