package com.example.tokentest.implement;

import com.example.tokentest.dto.ResponseBodyDTO;
import com.example.tokentest.entity.enumurations.CommonResStatus;
import com.example.tokentest.entity.master.repo.UserRepository;
import com.example.tokentest.security.AccessTokenAuthenticationToken;
import com.example.tokentest.security.jwt.JwtProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class CommonServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;


    ModelMapper modelMapper = new ModelMapper();

    public static String parseObjectToJSON(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        byte[] jsonBytes;
        try {
            jsonBytes = ow.writeValueAsBytes(obj);
            return new String(jsonBytes, StandardCharsets.UTF_8);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String setResponse(ResponseBodyDTO res) {
        return parseObjectToJSON(setResponse(res, CommonResStatus.getStatusCode(res.getCode()), CommonResStatus.getStatusCode(res.getCode()).getDesc()));
    }

    private ResponseBodyDTO setResponse(ResponseBodyDTO res, CommonResStatus status, String desc) {
        if (res == null) {
            res = new ResponseBodyDTO();
        }
        res.setMessage(desc);
        res.setCode(status.getCode());

        try {
            logger.info("Ended by response : " + new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(res));
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public ResponseBodyDTO setResponse(ResponseBodyDTO res, CommonResStatus status) {
        return setResponse(res, status, status.getDesc());
    }

    public AccessTokenAuthenticationToken getUserData() {
        return new AccessTokenAuthenticationToken();
    }

}
