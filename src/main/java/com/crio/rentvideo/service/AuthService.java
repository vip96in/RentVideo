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

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(UserDTO userDTO) {
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

    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new BadRequestException("Invalid email or password"));
        
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadRequestException("Invalid email or password");
        }

        return user;
    }
}
