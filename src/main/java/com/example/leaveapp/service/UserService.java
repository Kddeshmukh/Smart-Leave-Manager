package com.example.leaveapp.service;
import com.example.leaveapp.entity.User;
import com.example.leaveapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User registerUser(User user) {
        if (user.getRole() == null) user.setRole("STUDENT");
        return userRepository.save(user);
    }
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) return user;
        return null;
    }
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}