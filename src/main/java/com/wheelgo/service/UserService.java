package com.wheelgo.service;

import com.wheelgo.entity.User;
import com.wheelgo.entity.Vehicle;
import com.wheelgo.exception.VehicleNotAvailableException;
import com.wheelgo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByUserId(Long userId) {
        return userRepository.findById(userId);
    }

    public User findUser(Long userId) {
        User user = userRepository.findByUserId(userId);

        return user;
    }
}
