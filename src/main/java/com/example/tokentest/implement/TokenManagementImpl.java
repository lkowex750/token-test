package com.example.tokentest.implement;

import com.example.tokentest.dto.ResponseBodyDTO;
import com.example.tokentest.dto.ResponseJwt;
import com.example.tokentest.dto.tokenMangementDTO.ResponseUserDTO;
import com.example.tokentest.dto.tokenMangementDTO.user.RequestAddUserDTO;
import com.example.tokentest.dto.tokenMangementDTO.user.RequestLoginUser;
import com.example.tokentest.entity.enumurations.CommonResStatus;
import com.example.tokentest.entity.master.user.User;
import com.example.tokentest.interfaces.TokenManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TokenManagementImpl extends CommonServiceImpl implements TokenManagementService {

    private static final Logger logger = LoggerFactory.getLogger(TokenManagementImpl.class);

    @Override
    public ResponseBodyDTO getListUsers() {
        ResponseBodyDTO res = new ResponseBodyDTO();
        List<ResponseUserDTO> list = userRepository.findAll().stream().map(this::convertEntityToUserDTO).collect(Collectors.toList());
        res.setData(list);
        return setResponse(res, CommonResStatus.SUCCESS);
    }

    @Override
    public ResponseBodyDTO addUser(RequestAddUserDTO dto) {
        ResponseBodyDTO res = new ResponseBodyDTO();

        if (isNullOrEmpty(dto.getUsername())) {
            return setResponse(res, CommonResStatus.MISSING_USERNAME);
        } else if (isNullOrEmpty(dto.getEmail())) {
            return setResponse(res, CommonResStatus.MISSING_EMAIL);
        } else if (isNullOrEmpty(dto.getPassword())) {
            return setResponse(res, CommonResStatus.MISSING_PASSWORD);
        } else if (isNullOrEmpty(dto.getNameEN()) || isNullOrEmpty(dto.getNameTH())) {
            return setResponse(res, CommonResStatus.MISSING_NAME);
        } else if (isNullOrEmpty(dto.getLastnameEN()) || isNullOrEmpty(dto.getLastnameTH())) {
            return setResponse(res, CommonResStatus.MISSING_LASTNAME);
        } else if (isNullOrEmpty(dto.getPhoneNO())) {
            return setResponse(res, CommonResStatus.MISSING_PHONE);
        } else if (userRepository.existsByUsername(dto.getUsername())) {
            return setResponse(res, CommonResStatus.DUPLICATE_USERNAME);
        }

        //String username, String password, String nameTH, String lastnameTH, String nameEN, String lastnameEN, String email, String phoneNO, String date
        String base64Password = new BCryptPasswordEncoder().encode(dto.getPassword());
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        User user = new User(dto.getUsername(), base64Password, dto.getNameTH(), dto.getLastnameTH(), dto.getNameEN(), dto.getLastnameEN(), dto.getEmail(), dto.getPhoneNO(), date);
        userRepository.save(user);
        return setResponse(res, CommonResStatus.SUCCESS);
    }

    @Override
    public ResponseBodyDTO login(RequestLoginUser dto) {
        ResponseBodyDTO res = new ResponseBodyDTO();
        if (isNullOrEmpty(dto.getUsername()) || isNullOrEmpty(dto.getPassword())) {
            return setResponse(res, CommonResStatus.INVALID_FORM_LOGIN);
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername(),
                        dto.getPassword()
                )
        );
        logger.info(authentication.toString());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        ResponseJwt responseJwt = new ResponseJwt(jwt);
        res.setData(responseJwt);
        return setResponse(res, CommonResStatus.SUCCESS);
    }

    private ResponseUserDTO convertEntityToUserDTO(User user) {
        return modelMapper.map(user, ResponseUserDTO.class);

    }

    private boolean isNullOrEmpty(String data) {
        return data.isEmpty() || data.isBlank() || data.contains(" ");
    }

}