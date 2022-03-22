package com.example.tokentest.dto;


import com.example.tokentest.entity.enumurations.CommonResStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseBodyDTO {
    private String action;
    private String code = CommonResStatus.SUCCESS.getCode();
    private String message = CommonResStatus.SUCCESS.getDesc();
    private String stateCode;
    private Object data = null;

    public ResponseBodyDTO() {
    }

    public ResponseBodyDTO(CommonResStatus status) {
        this.code = status.getCode();
        this.message = status.getDesc();
    }

    public ResponseBodyDTO(Object data, CommonResStatus status) {
        this.code = status.getCode();
        this.message = status.getDesc();
        this.data = data;
    }

    public ResponseBodyDTO(String action, String responseCode, String responseMessage, String stateCode, Object data) {
        this.action = action;
        this.code = responseCode;
        this.message = responseMessage;
        this.stateCode = stateCode;
        this.data = data;
    }
}
