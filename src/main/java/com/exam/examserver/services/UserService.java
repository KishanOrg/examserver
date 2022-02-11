package com.exam.examserver.services;

import com.exam.examserver.model.User;
import com.exam.examserver.model.UserRole;

import java.util.Set;

public interface UserService {
    // create user
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    //get user by username
    public  User getUser(String username);

    //delete user by username
    public void deleteUser(String username);

    //update user by username
//    public User updateUser(String username);

    //save user
    public User saveUser(User user);

}
