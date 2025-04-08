package com.josias.manageuser.service;

import com.josias.manageuser.model.Role;
import com.josias.manageuser.model.User;
import com.josias.manageuser.repository.RoleRepository;
import com.josias.manageuser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(User user, String roleName) {
        Role role = roleRepository.findByName(roleName);
        user.setRoles(Set.of(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
