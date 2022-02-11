package com.exam.examserver.repo;

import com.exam.examserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // find user by username
    public User findByUsername(String username);

    // delete user by username
    public void deleteByUsername(String username);

    // update user by username
//    public User updateUsername(String username);
}
