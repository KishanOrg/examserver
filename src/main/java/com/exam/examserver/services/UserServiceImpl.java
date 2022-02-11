package com.exam.examserver.services;

import com.exam.examserver.helper.UserFoundException;
import com.exam.examserver.model.User;
import com.exam.examserver.model.UserRole;
import com.exam.examserver.repo.RoleRepository;
import com.exam.examserver.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository; // the implementations of the class this object is done by spring boot
    @Autowired
    private RoleRepository roleRepository;

    //Creating User
    @Override
    public User createUser (User user, Set<UserRole> userRoles) throws Exception {
        User local = this.userRepository.findByUsername(user.getUsername());

        if(local != null){
            System.out.println("User is already there !!");
            throw new UserFoundException();
        }else {
            //create user
            for(UserRole ur: userRoles){
                roleRepository.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);
            local = this.userRepository.save(user);
        }


        return local;
    }

    // Get user by username
    @Override
    public User getUser(String username) {

        return this.userRepository.findByUsername(username);
    }

    // Delete user by username
    @Override
    public void deleteUser(String username) {

        this.userRepository.deleteByUsername(username);

    }

    @Override
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }



}
