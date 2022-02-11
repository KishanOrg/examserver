package com.exam.examserver.helper;

public class UserNotFoundException extends Exception{

    public UserNotFoundException() {
        super("User with this credentials doesn't exist in the database!!");
    }

    public UserNotFoundException (String msg) {
        super(msg);
    }

}
