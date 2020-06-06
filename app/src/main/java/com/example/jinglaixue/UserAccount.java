package com.example.jinglaixue;

public class UserAccount {
    private String token;

    private static  UserAccount instance = null;

    private  UserAccount(){

    }
    public synchronized static UserAccount shard(){
        if(instance==null){
            instance = new UserAccount();
        }
        return instance;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
