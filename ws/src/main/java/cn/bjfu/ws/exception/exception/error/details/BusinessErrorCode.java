package cn.bjfu.ws.exception.exception.error.details;

import lombok.Getter;

/**
 * 业务通用异常枚举
 *
 * @author purgeyao
 * @since 1.0
 */
@Getter
public enum BusinessErrorCode {

    /**
     * 通用业务异常
     */
    BUSINESS_ERROR("CLOUD800","业务异常"),
    ;

    private String code;

    private String message;

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

    BusinessErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
