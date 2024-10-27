package com.crio.rentvideo.service;

import com.crio.rentvideo.dto.UserDTO;
import com.crio.rentvideo.enums.RoleType;
import com.crio.rentvideo.exception.BadRequestException;
import com.crio.rentvideo.model.Role;
import com.crio.rentvideo.model.User;
import com.crio.rentvideo.repository.RoleRepository;
import com.crio.rentvideo.repository.UserRepository;
import com.crio.rentvideo.utils.JwtUtil;

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

    @Autowired
    private JwtUtil jwtUtil;

    public User registerUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new BadRequestException("Email already in use");
        }

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());

        // Set role, default to CUSTOMER if not specified
        Role role = roleRepository.findByRole(userDTO.getRole())
            .orElseGet(() -> roleRepository.findByRole(RoleType.CUSTOMER)
            .orElseThrow(() -> new BadRequestException("Role not found")));
        user.setRole(role);

        return userRepository.save(user);
    }

    public String loginUser(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid email or password"));

        // Check if the provided password matches the stored password
        if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid email or password");
        }

        // Generate JWT token
        return jwtUtil.generateToken(user.getEmail());
    }
}
