package com.example.tokentest.controller;

import com.example.tokentest.dto.ResponseBodyDTO;
import com.example.tokentest.dto.tokenMangementDTO.RequestRefreshTokenDTO;
import com.example.tokentest.dto.tokenMangementDTO.user.RequestAddUserDTO;
import com.example.tokentest.dto.tokenMangementDTO.user.RequestLoginUser;
import com.example.tokentest.dto.tokenMangementDTO.user.RequestUpdateUserDTO;
import com.example.tokentest.implement.CommonServiceImpl;
import com.example.tokentest.interfaces.TokenManagementService;
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
}
