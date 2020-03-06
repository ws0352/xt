package cn.bjfu.ws.exception.exception.category;


import cn.bjfu.ws.exception.exception.error.CommonErrorCode;
import cn.bjfu.ws.exception.exception.error.details.BusinessErrorCode;
import lombok.Getter;

/**
 * {@link RuntimeException} 通用业务异常
 *
 * @author purgeyao
 * @since 1.0
 */
@Getter
public class BusinessException extends RuntimeException {

  private String code;
  private boolean isShowMsg = true;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public boolean isShowMsg() {
    return isShowMsg;
  }

  public void setShowMsg(boolean showMsg) {
    isShowMsg = showMsg;
  }

  /**
   * 使用枚举传参
   *
   * @param errorCode 异常枚举
   */
  public BusinessException(BusinessErrorCode errorCode) {
    super(errorCode.getMessage());
    this.code = errorCode.getCode();
  }
  
  /**
   * 使用CommonErrorCode枚举传参
   *
   * @param errorCode 异常枚举
   */
  public BusinessException(CommonErrorCode errorCode) {
    super(errorCode.getMessage());
    this.code = errorCode.getCode();
  }

  /**
   * 使用自定义消息
   *
   * @param code 值
   * @param msg 详情
   */
  public BusinessException(String code, String msg) {
    super(msg);
    this.code = code;
  }

}
