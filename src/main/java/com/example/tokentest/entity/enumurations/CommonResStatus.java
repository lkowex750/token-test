package com.example.tokentest.entity.enumurations;

public enum CommonResStatus {
    SUCCESS("0000","success"),
    MISSING_USERNAME("1000" , "not found username"),
    MISSING_NAME("1001" , "not found name"),
    MISSING_LASTNAME("1002" , "not found lastname"),
    MISSING_EMAIL("1003" , "not found email"),
    MISSING_PHONE("1004" , "not found phone number"),
    MISSING_PASSWORD("1005" , "not found password"),
    DUPLICATE_USERNAME("1006", "user is duplicate"),
    INVALID_FORM_LOGIN("1007", "invalid form data"),
    FAILED_LOGIN("1008", "failed login"),
    UNAUTHORIZED("1009", "unauthorized access"),
    NOT_FOUND_USERNAME("1010", "username not found");

    private final String code;
    private final String desc;

    CommonResStatus(final String code, final String desc){
        this.code = code;
        this.desc = desc;
    }
    public String getCode(){return code;}

    public String getDesc(){return desc;}

    public static CommonResStatus getStatusCode(final String value){
        for(CommonResStatus c : values()){
            if(c.code.equalsIgnoreCase(value)){
                return  c;
            }
        }
        return null;
    }

}
