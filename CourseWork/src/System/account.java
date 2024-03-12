package System;

public class account {
    private String acoount;
    private String password;
    account(){
        this.acoount="Null";
        this.password="0";
    }
    account(String account,String password){
        this.acoount=account;
        this.password=password;
    }
    public String getAccount(){
        return acoount;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String newPassword){
        this.password=newPassword;
        System.out.println("New password is "+newPassword);
    }
}
