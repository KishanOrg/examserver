package com.exam.examserver.helper;

public class UserFoundException extends Exception{

    public UserFoundException() {
        super("User with this credentials already exist in the database!!");
    }

    public UserFoundException (String msg) {
        super(msg);
    }

}
