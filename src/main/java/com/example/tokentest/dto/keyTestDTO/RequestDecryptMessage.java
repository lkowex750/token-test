package com.example.tokentest.dto.keyTestDTO;

import lombok.Data;

@Data
public class RequestDecryptMessage {
    private String public_key;
    private String private_key;
    private String encryptedMessage;

}
