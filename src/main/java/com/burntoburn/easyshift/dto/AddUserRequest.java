package com.burntoburn.easyshift.dto;

import com.burntoburn.easyshift.entity.user.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {

    private String email;
    private String phoneNumber;
    private Role role;
    private String avatarUrl;

}
