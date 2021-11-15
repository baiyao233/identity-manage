package com.baiyao.identity.to;


import com.baiyao.identity.config.ErrorCode;

/**
 * @author baiyao
 * @date 2021/10/11 14:04
 * @description
 */
public class ErrorResultTO {
    private String errorCode;
    private String errorInfo = "";

    public ErrorResultTO() {
    }

    private ErrorResultTO(String errorCode, String errorInfo) {
        this.errorCode = errorCode;
        this.errorInfo = errorInfo;
    }

    public static ErrorResultTO createFailInstance(String errorCode, String errorInfo) {
        return new ErrorResultTO(errorCode, errorInfo);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }


    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }


    public static ErrorResultTO getIntenalServerError() {
        return new ErrorResultTO(ErrorCode.INTERNAL_ERROR.getErrorCode(),
                ErrorCode.INTERNAL_ERROR.getErrorInfo());
    }
}
