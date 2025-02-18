package com.burntoburn.easyshift.service;

import com.burntoburn.easyshift.dto.AddUserRequest;
import com.burntoburn.easyshift.entity.user.User;
import com.burntoburn.easyshift.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService  {

    private final UserRepository userRepository;


    public User save(AddUserRequest request) {
        User newUser = User.builder()
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .role(request.getRole())
                .avatarUrl(request.getAvatarUrl())
                .build();
        return userRepository.save(newUser);
    }
}
