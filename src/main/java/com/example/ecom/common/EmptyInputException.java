package com.example.ecom.common;

public class EmptyInputException   extends  RuntimeException{
//        extends  RuntimeException{
private static final long serialVersionUID = 1L;
    private String errorCode;
    private String errorMEssage;

        public EmptyInputException(String errorCode, String errorMEssage) {
            this.errorCode = errorCode;
            this.errorMEssage = errorMEssage;
        }

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

        public EmptyInputException() {

        }

}
