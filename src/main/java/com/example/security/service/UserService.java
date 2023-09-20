package com.example.security.service;

import com.example.security.entity.User;
import com.example.security.exception.RoleNotFoundException;
import com.example.security.exception.UserAlreadyExistsException;
import com.example.security.repo.RoleRepo;
import com.example.security.repo.UserRepo;
import com.example.security.type.ErrorMassageType;
import com.example.security.type.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;

    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(
                        String.format(
                                ErrorMassageType.USER_NOT_FOUND_SF.getText(), username
                        )
                )
        );
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .toList()

        );
    }

    public void createNewUser(User user) throws UserAlreadyExistsException, RoleNotFoundException {
        if (findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException(
                    String.format(
                            ErrorMassageType.USER_ALREADY_EXIST_SF.getText(), user.getUsername()
                    )
            );
        }
        user.setRoles(List.of(
                roleRepo.findByName(RoleType.USER_ROLE.toString())
                        .orElseThrow(()-> new RoleNotFoundException(
                                String.format(
                                        ErrorMassageType.ROLE_NOT_FOUND_SF.getText(),RoleType.USER_ROLE
                                )
                        ))
        ));

        userRepo.save(user);
    }
}
