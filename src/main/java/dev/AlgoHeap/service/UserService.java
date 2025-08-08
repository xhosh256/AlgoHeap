package dev.AlgoHeap.service;

import org.springframework.stereotype.Service;

import dev.AlgoHeap.model.User;
import dev.AlgoHeap.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findByNameAndPassword(String name, String password) {
        return userRepository.findByNameAndPassword(name, password);
    }
}
