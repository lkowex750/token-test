package com.example.tokentest.dto.tokenMangementDTO;

import lombok.Data;

@Data
public class RequestSearchDTO {
    private String username = "";
    private String nameTH = "";
    private String lastnameTH = "";
    private String nameEN = "";
    private String lastnameEN = "";
    private String email = "";
    private String phoneNO = "";
}
