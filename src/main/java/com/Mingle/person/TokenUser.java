package com.Mingle.person;

import lombok.Data;

@Data
public class TokenUser {
    private String token;
    private Admin admin;
}
