package com.challenge.arts.week30;

public class UserService {

    private User user;

    public UserService() {
    }

    public UserService(User user) {
        this.user = user;
    }

    public String getUserName(){
        return this.user.getName();
    }

    public Integer getUserAge(){
        return this.user.getAge();
    }
}
