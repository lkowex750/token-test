package com.example.tokentest.interfaces;

import com.example.tokentest.dto.ResponseBodyDTO;
import com.example.tokentest.dto.tokenMangementDTO.user.RequestAddUserDTO;
import com.example.tokentest.dto.tokenMangementDTO.user.RequestLoginUser;

public interface TokenManagementService {
    ResponseBodyDTO getListUsers();
    ResponseBodyDTO addUser(RequestAddUserDTO dto);
    ResponseBodyDTO login(RequestLoginUser dto);
}
