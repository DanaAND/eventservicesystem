package com.sda.lukaapp.exception;

public class NotFoundException extends BaseException {

    private ErrorCode errorCode;

    public NotFoundException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    @Override
    public String getCode() {
        return this.errorCode.getCode();
    }

    public enum ErrorCode {
        USER_NOT_FOUND("user_not_found"),
        CATEGORY_NOT_FOUND("category_not_found"),
        MY_ENTITY_NOT_FOUND("my_entity_not_found");

        private String code;

        ErrorCode(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
}

