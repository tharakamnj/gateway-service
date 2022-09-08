package com.booking.gatewayservice.dto;

import lombok.Getter;

@Getter
public class UserDTO {

    private Long userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String role;
    private String email;
    private String mobile;
    private String password;
}
