package com.exam.examserver.controller;
import  com.exam.examserver.model.Email;
import com.exam.examserver.helper.UserFoundException;
import com.exam.examserver.helper.UserNotFoundException;
import com.exam.examserver.model.Authority;
import com.exam.examserver.model.Role;
import com.exam.examserver.model.User;
import com.exam.examserver.model.UserRole;
import com.exam.examserver.services.EmailService;
import com.exam.examserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.Session;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController  {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/getOTP", method = RequestMethod.POST)
    public ResponseEntity getOTP(@RequestBody Email email, HttpServletRequest request){
        System.out.println(email);

        HttpSession session = request.getSession();
//        session.setMaxInactiveInterval(30);
        int sendOTP = emailService.sendOTP(email.getTo());



        if(sendOTP != -1){
            session.setAttribute("sentOTP", sendOTP);
            return ResponseEntity.ok("Message Sent successfully................");
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send message.................");
        }
    }

    @RequestMapping(value = "/validateOTP", method = RequestMethod.POST)
    public ResponseEntity validateOTP(@RequestBody Email email, HttpServletRequest request){

        HttpSession session = request.getSession();

//        session.setMaxInactiveInterval(100);
        String sentOTP = "" + session.getAttribute("sentOTP");
        String receivedOTP = "" + email.getReceivedOTP();

        System.out.println();
        boolean res = sentOTP.equals(receivedOTP);
        if(res){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("OTP Validated Successfully");
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Wrong OTP" );
        }

    }


    //Creating User
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {

            Set<UserRole> userRoleSet = new HashSet<>();

            Role role = new Role();
            role.setRoleName("Normal");
            role.setRoleId(101);

            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);

            userRoleSet.add(userRole);

            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

            return this.userService.createUser(user, userRoleSet);


    }

    //Getting User with username
    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username){
        return  this.userService.getUser(username);
    }

    //Deleting user with username
    @DeleteMapping("/{username}")
    @Transactional
    public void deleteUser(@PathVariable("username") String username){
        this.userService.deleteUser(username);
    }

    //Update user by username
    @PutMapping("/")
    public User updateUser(@RequestBody User user){
        User existingUser = this.userService.getUser(user.getUsername());
        existingUser.setFirstname((user.getFirstname() != null) ? user.getFirstname() : existingUser.getFirstname());
        existingUser.setLastname((user.getLastname() != null) ? user.getLastname() : existingUser.getLastname());
        existingUser.setPhone((user.getPhone() != null) ? user.getPhone() : existingUser.getPhone());
        existingUser.setProfile((user.getProfile() != null) ? user.getProfile() : existingUser.getProfile());
        existingUser.setEmail((user.getEmail() != null) ? user.getEmail() : existingUser.getEmail());
        existingUser.setPassword((user.getPassword() != null) ? user.getPassword() : existingUser.getPassword());
        return  this.userService.saveUser(existingUser);
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserFoundException ex) {
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);

    }



}
