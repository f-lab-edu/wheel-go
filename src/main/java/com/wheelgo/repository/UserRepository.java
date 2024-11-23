package com.wheelgo.repository;

import com.wheelgo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Long findByUsername(String username);

    User findByUserId(Long userId);
}
