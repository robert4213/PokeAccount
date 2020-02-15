package android.cmpe277.pokeaccount;

import java.util.UUID;

public class Account {
    private UUID id;
    private String type;
    private double balance;
    private String company;

    public Account(){
        id = UUID.randomUUID();
    }

    public Account(UUID uuid){id = uuid;}

    public Account(String type, double balance, String company){
        id = UUID.randomUUID();
        this.type = type;
        this.balance = balance;
        this.company = company;
    }

    public UUID getid(){
        return id;
    }

    public String getType(){
        return type;
    }

    public double getBalance(){
        return balance;
    }

    public String getCompany(){
        return company;
    }

    public void setBalance(double value){
        balance = value;
    }

    public void setType(String type){ this.type = type;}

    public  void setCompany(String company){ this.company = company;}

    public Boolean newAccount(){
        if(getCompany() == null || getType() == null) return true;
        else return false;
    }
}
