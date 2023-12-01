package com.example.ecom.common;

public class commonException extends  RuntimeException{
    private String errorCode;
    private String errorMEssage;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMEssage() {
        return errorMEssage;
    }

    public void setErrorMEssage(String errorMEssage) {
        this.errorMEssage = errorMEssage;
    }

    public commonException(String errorCode, String errorMEssage) {
        this.errorCode = errorCode;
        this.errorMEssage = errorMEssage;
    }

    public commonException()
    {
       super();
    }


}
