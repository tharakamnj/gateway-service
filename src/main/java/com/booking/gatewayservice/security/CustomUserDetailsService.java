package com.booking.gatewayservice.security;

import com.booking.gatewayservice.dto.UserDTO;
import com.booking.gatewayservice.proxyClient.CoreServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private CoreServiceClient coreServiceClient;

    private final WebClient.Builder webClientBuilder;

    @Autowired
    public CustomUserDetailsService(CoreServiceClient coreServiceClient, WebClient.Builder webClientBuilder) {
        this.coreServiceClient = coreServiceClient;
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        UserDTO user = new UserDTO();
        try {
            user = coreServiceClient.getUserByUserName(username);
            System.out.println(user);

        }catch (Exception e){
            logger.debug(e.getMessage());
        }

        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),new ArrayList<>());
    }
}
