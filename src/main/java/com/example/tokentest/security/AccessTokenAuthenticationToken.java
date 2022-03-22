package com.example.tokentest.security;

import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;


@Data
public class AccessTokenAuthenticationToken {
    private String username;

    public AccessTokenAuthenticationToken() {
        this.username = SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
