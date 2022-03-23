package com.example.tokentest.interfaces;

import com.example.tokentest.dto.ResponseBodyDTO;
import com.example.tokentest.dto.tokenMangementDTO.RequestRefreshTokenDTO;
import com.example.tokentest.dto.tokenMangementDTO.user.RequestAddUserDTO;
import com.example.tokentest.dto.tokenMangementDTO.user.RequestLoginUser;
import com.example.tokentest.dto.tokenMangementDTO.user.RequestUpdateUserDTO;
import com.example.tokentest.security.AccessTokenAuthenticationToken;

public interface TokenManagementService {
    ResponseBodyDTO getListUsers();
    ResponseBodyDTO addUser(RequestAddUserDTO dto);
    ResponseBodyDTO login(RequestLoginUser dto);
    ResponseBodyDTO updateUser(RequestUpdateUserDTO dto, AccessTokenAuthenticationToken token);
    ResponseBodyDTO refreshToken(RequestRefreshTokenDTO dto);
}
