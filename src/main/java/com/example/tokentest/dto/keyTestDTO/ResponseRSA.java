package com.example.tokentest.dto.keyTestDTO;

import lombok.Data;

@Data
public class ResponseRSA {
    private String public_key;
    private String private_key;

    public ResponseRSA(String public_key, String private_key) {
        this.public_key = public_key;
        this.private_key = private_key;
    }
}
