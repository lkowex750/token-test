package com.example.tokentest.entity.master.repo;

import com.example.tokentest.entity.master.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User,String> , JpaSpecificationExecutor<User> {
    boolean existsByUsername(String username);

}
