package com.baiyao.identity.to;

/**
 * @author baiyao
 * @date 2021/10/11 19:25
 * @description
 */
public class SuccessResultTO {
    private Object response;

    private SuccessResultTO(Object response) {
        this.response = response;
    }

    public static SuccessResultTO createSuccessInstance(Object response) {
        return new SuccessResultTO(response);
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
