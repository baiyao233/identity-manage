package com.baiyao.identity.config;

import org.apache.commons.lang3.StringUtils;

/**
 * @author baiyao
 * @date 2021/10/11 14:04
 * @description 定义接口返回规范
 */
public enum ErrorCode {
    /**
     * 成功
     */
    SUCCESS("success", "success", 200),
    /**
     * body 为空
     */
    EMPTY_REQUEST_BODY("EmptyRequestBody", "Empty Request Body", 400),
    /**
     * body 无效
     */
    INVALID_REQUEST_BODY("InvalidRequestBody", "Invalid Request Body", 400),
    /**
     * 某参数重复（%s 是占位符，实际调用会给出明确的参数名或提示。）
     */
    REPEATED_PARAMETER("RepeatedParameter", "The specified %s is repeated.", 400),
    /**
     * 缺少参数 %s
     */
    MISSING_PARAMETER("MissingParameter", "The %s is mandatory for this action.", 400),
    /**
     * 参数值无效
     */
    INVALID_PARAMETER("InvalidParameter", "The specified parameter %s value is not valid.", 400),
    /**
     * 找不到资源  根据指定的参数%s找不到资源，建议检查%s是否正确。
     */
    NOT_FOUND_RESOURCE("NotFoundResource", "Cannot find resource according to your specified %s.", 400),
    /**
     * 参数格式错误
     */
    INVALID_FORMAT("InvalidFormat", "The specified parameter %s value is not well formatted.", 400),
    /**
     * 参数值重复
     */
    DUPLICATE_PARAMETER("DuplicateParameter", "The specified parameter %s value is duplicate.", 400),
    /**
     * 参数依赖错误 指定参数被依赖，不能随意删除，请先解除依赖。
     */
    DEPENDENCY_VIOLATION_PARAMETER("DependencyViolationParameter", "The specified %s has %s definitions.", 400),
    /**
     * 超出个数限制
     */
    EXCEED_LIMIT("ExceedLimit", "The specified %s count exceeds the limit.", 400),
    /**
     * 长度超限
     */
    LENGTH_LIMIT("LengthLimit", "The parameter %s length exceeds the limit.", 400),
    /**
     * 参数不能为空
     */
    NULL_POINT_ERROR("NullPointError", "The specified parameter %s value is null.", 400),
    /**
     * 参数类型错误
     */
    PARAMETER_TYPE_ERROR("ParameterTypeError", "The specified parameter %s  type error.", 400),
    /**
     * 身份权限校验异常
     */
    IDENTITY_VALIDATION_EXCEPTION("IdentityValidationException", "Identity Validation  Exception", 401),
    /**
     * 服务内部错误
     */
    INTERNAL_ERROR("InternalError", "Internal Error", 500),
    /**
     * 存在
     */
    EXIST("Exist", "%s is exist.", 400),
    /**
     * 不存在
     */
    NOT_EXIST("NotExist", "%s is not exist.", 404),

    ;
    private String errorCode;
    private String errorInfo;
    private int responseStatus;

    private ErrorCode(String errorCode, String errorInfo, int responseStatus) {
        this.errorCode = errorCode;
        this.errorInfo = errorInfo;
        this.responseStatus = responseStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorCode(String context, String parameter) {
        return errorCode + "." + context + "." + parameter;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public String getErrorInfo(String parameter) {
        return StringUtils.replaceOnce(this.errorInfo, "%s", parameter);
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }
}
