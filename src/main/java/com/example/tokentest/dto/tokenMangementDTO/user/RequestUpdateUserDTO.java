package com.example.tokentest.dto.tokenMangementDTO.user;

import lombok.Data;

@Data
public class RequestUpdateUserDTO {

    private String password;
    private String nameTH;
    private String lastnameTH;
    private String nameEN;
    private String lastnameEN;
    private String email;
    private String phoneNO;
}
