package com.crio.rentvideo.service;

import com.crio.rentvideo.dto.UserDTO;
import com.crio.rentvideo.enums.RoleType;
import com.crio.rentvideo.exception.BadRequestException;
import com.crio.rentvideo.model.Role;
import com.crio.rentvideo.model.User;
import com.crio.rentvideo.repository.RoleRepository;
import com.crio.rentvideo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // CRUD Operations

    public User createUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new BadRequestException("Email already in use");
        }

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());

        // Set role
        Role role = roleRepository.findByRole(userDTO.getRole()).orElseGet(() ->
            roleRepository.findByRole(RoleType.CUSTOMER).orElseThrow(() -> new BadRequestException("Role not found"))
        );
        user.setRole(role);

        return userRepository.save(user);
    }

    public User updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new BadRequestException("User not found"));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new BadRequestException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
