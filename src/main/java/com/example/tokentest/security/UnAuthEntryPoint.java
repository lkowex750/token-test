package com.example.tokentest.security;

import com.example.tokentest.entity.enumurations.CommonResStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class UnAuthEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(UnAuthEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        logger.error("UnAuthorized error. Message - {}", authException.getMessage());
        response.setHeader("error", authException.getMessage());
        Map<String, String> tokens = new HashMap<>();
        if(authException.getMessage().equals("Bad credentials")){
            tokens.put("code ",CommonResStatus.FAILED_LOGIN.getCode());
            tokens.put("message ", CommonResStatus.FAILED_LOGIN.getDesc());
        }else{
            tokens.put("code ",CommonResStatus.UNAUTHORIZED.getCode());
            tokens.put("message ", CommonResStatus.UNAUTHORIZED.getDesc());
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(),tokens);

    }
}
