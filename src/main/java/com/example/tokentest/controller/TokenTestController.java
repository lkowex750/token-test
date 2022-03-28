package com.example.tokentest.controller;

import com.example.tokentest.dto.*;
import com.example.tokentest.dto.keyTestDTO.*;
import com.example.tokentest.dto.tokenMangementDTO.RequestRefreshTokenDTO;
import com.example.tokentest.dto.tokenMangementDTO.RequestSearchDTO;
import com.example.tokentest.dto.tokenMangementDTO.user.RequestAddUserDTO;
import com.example.tokentest.dto.tokenMangementDTO.user.RequestLoginUser;
import com.example.tokentest.dto.tokenMangementDTO.user.RequestUpdateUserDTO;
import com.example.tokentest.implement.CommonServiceImpl;
import com.example.tokentest.interfaces.TokenManagementService;
import com.example.tokentest.security.RSA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/token-test")
public class TokenTestController extends CommonServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(TokenTestController.class);
    @Autowired
    TokenManagementService tokenManagementService;

    @GetMapping(value = "/list-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getListUsers() {
        ResponseBodyDTO res = tokenManagementService.getListUsers();
        return setResponse(res);
    }

    @PostMapping(value = "/add-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addUser(@RequestBody RequestAddUserDTO dto) {
        ResponseBodyDTO res = tokenManagementService.addUser(dto);
        return setResponse(res);
    }

    @PostMapping(value = "/update-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateUser(@RequestBody RequestUpdateUserDTO dto) {
        ResponseBodyDTO res = tokenManagementService.updateUser(dto, getUserData());
        return setResponse(res);
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public String loginUser(@RequestBody RequestLoginUser dto) {
        logger.info(dto.getUsername() + dto.getPassword());

        ResponseBodyDTO res = tokenManagementService.login(dto);
        return setResponse(res);
    }

    @PostMapping(value = "/auth/refresh-token", produces = MediaType.APPLICATION_JSON_VALUE)
    public String refreshToken(@RequestBody RequestRefreshTokenDTO dto) {
        ResponseBodyDTO res = tokenManagementService.refreshToken(dto);
        return setResponse(res);
    }

    @GetMapping(value = "getKey", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseRSA getKey(){
        RSA rsa = new RSA();
        ResponseRSA res = new ResponseRSA(rsa.getPublicKey(),rsa.getPrivateKey());

        return res;
    }

    @PostMapping(value = "test-key",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseTestKey testKey(@RequestBody RequestBodyTestKey dto){
        RSA rsa = new RSA();
        rsa.setKeyPair(dto.getPublic_key(),dto.getPrivate_key());
        String encryptMessage = "";
        try {
            encryptMessage = rsa.encrypt(dto.getMessage());

        }catch (Exception e){logger.info(e.getMessage());}

        ResponseTestKey res = new ResponseTestKey();
        res.setEncryptedMessage(encryptMessage);

        return res;
    }

    @PostMapping(value = "decrypted-message", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDecryptMessage decryptMessage(@RequestBody RequestDecryptMessage dto){
        RSA rsa = new RSA();
        rsa.setKeyPair(dto.getPublic_key(),dto.getPrivate_key());
        String decryptMessage = "";
        try {
            decryptMessage = rsa.decrypt(dto.getEncryptedMessage());
        }catch (Exception e){logger.info(e.getMessage());}

        ResponseDecryptMessage res = new ResponseDecryptMessage();
        res.setMessage(decryptMessage);
        return res;
    }

    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public String searchUser(@RequestBody RequestSearchDTO dto){
        ResponseBodyDTO res = tokenManagementService.search(dto);
        return setResponse(res);
    }
}
