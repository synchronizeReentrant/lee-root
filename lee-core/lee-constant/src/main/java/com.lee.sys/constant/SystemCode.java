package com.lee.sys.constant;

public enum  SystemCode {
    SUCCESS("0000","操作成功"),
    ERROR("9999","系统错误"),

    ;
    private String code;
    private String message;
    private SystemCode(String code,String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
