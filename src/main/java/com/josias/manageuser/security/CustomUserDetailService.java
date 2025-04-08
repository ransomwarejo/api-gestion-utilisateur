package com.josias.manageuser.security;


import com.josias.manageuser.model.User;
import com.josias.manageuser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService  implements UserDetailsService {

    private final UserRepository userRepository;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(), // Assure-toi que le password est haché (BCrypt)
                this.authorities = user.getRoles().stream()
                        .flatMap(role -> Stream.concat(
                                Stream.of(new SimpleGrantedAuthority("ROLE_" + role.getName())),
                                role.getPermissions().stream()
                                        .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                        ))
                        .collect(Collectors.toSet())
        );
    }


}
