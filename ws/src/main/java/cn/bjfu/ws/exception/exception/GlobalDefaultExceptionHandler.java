package cn.bjfu.ws.exception.exception;

import cn.bjfu.ws.exception.Result;
import cn.bjfu.ws.exception.exception.category.BusinessException;
import cn.bjfu.ws.exception.exception.error.CommonErrorCode;
import com.netflix.client.ClientException;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * {@link RestControllerAdvice} 基础全局异常处理
 *
 * @author purgeyao
 * @since 1.0
 */
@RestControllerAdvice
public class GlobalDefaultExceptionHandler {

    private final static Logger log = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    /**
     * NoHandlerFoundException 404 异常处理
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result handlerNoHandlerFoundException(NoHandlerFoundException exception) {
        outPutErrorWarn(NoHandlerFoundException.class, CommonErrorCode.NOT_FOUND, exception);
        return Result.ofFail(CommonErrorCode.NOT_FOUND);
    }

    /**
     * HttpRequestMethodNotSupportedException 405 异常处理
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handlerHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException exception) {
        outPutErrorWarn(HttpRequestMethodNotSupportedException.class,
                CommonErrorCode.METHOD_NOT_ALLOWED, exception);
        return Result.ofFail(CommonErrorCode.METHOD_NOT_ALLOWED);
    }

    /**
     * HttpMediaTypeNotSupportedException 415 异常处理
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result handlerHttpMediaTypeNotSupportedException(
            HttpMediaTypeNotSupportedException exception) {
        outPutErrorWarn(HttpMediaTypeNotSupportedException.class,
                CommonErrorCode.UNSUPPORTED_MEDIA_TYPE, exception);
        return Result.ofFail(CommonErrorCode.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * Exception 类捕获 500 异常处理
     */
    @ExceptionHandler(value = Exception.class)
    public Result handlerException(Exception e) {
        return ifDepthExceptionType(e);
    }

    /**
     * 二次深度检查错误类型
     */
    private Result ifDepthExceptionType(Throwable throwable) {
        Throwable cause = throwable.getCause();
        if (cause instanceof ClientException) {
            return handlerClientException((ClientException) cause);
        }
        if (cause instanceof FeignException) {
            return handlerFeignException((FeignException) cause);
        }
        outPutError(Exception.class, CommonErrorCode.EXCEPTION, throwable);
        return Result.ofFail(CommonErrorCode.EXCEPTION);
    }

    /**
     * FeignException 类捕获
     */
    @ExceptionHandler(value = FeignException.class)
    public Result handlerFeignException(FeignException e) {
        outPutError(FeignException.class, CommonErrorCode.RPC_ERROR, e);
        return Result.ofFail(CommonErrorCode.RPC_ERROR);
    }

    /**
     * ClientException 类捕获
     */
    @ExceptionHandler(value = ClientException.class)
    public Result handlerClientException(ClientException e) {
        outPutError(ClientException.class, CommonErrorCode.RPC_ERROR, e);
        return Result.ofFail(CommonErrorCode.RPC_ERROR);
    }

    /**
     * BusinessException 类捕获
     */
    @ExceptionHandler(value = BusinessException.class)
    public Result handlerBusinessException(BusinessException e) {
        outPutError(BusinessException.class, CommonErrorCode.BUSINESS_ERROR, e);
        return Result.ofFail(e.getCode(), e.getMessage());
    }

    /**
     * HttpMessageNotReadableException 参数错误异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        outPutError(HttpMessageNotReadableException.class, CommonErrorCode.PARAM_ERROR, e);
        String msg = String.format("%s : 错误详情( %s )", CommonErrorCode.PARAM_ERROR.getMessage(),
                e.getRootCause().getMessage());
        return Result.ofFail(CommonErrorCode.PARAM_ERROR.getCode(), msg);
    }

    /**
     * ConstraintViolationException 参数错误异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result handleConstraintViolationException(ConstraintViolationException ex) {
        String smg = "";
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        if (log.isDebugEnabled()) {
            for (ConstraintViolation error : constraintViolations) {
                log.error("{} -> {}", error.getPropertyPath(), error.getMessageTemplate());
                smg = error.getMessageTemplate();
            }
        }

        if (constraintViolations.isEmpty()) {
            log.error("validExceptionHandler error fieldErrors is empty");
            Result.ofFail(CommonErrorCode.BUSINESS_ERROR.getCode(), "");
        }

        return Result.ofFail(CommonErrorCode.PARAM_ERROR.getCode(), smg);
    }

    /**
     * MethodArgumentNotValidException 参数错误异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        return getBindResultDTO(bindingResult);
    }

    /**
     * BindException 参数错误异常
     */
    @ExceptionHandler(BindException.class)
    public Result handleBindException(BindException e) {
        outPutError(BindException.class, CommonErrorCode.PARAM_ERROR, e);
        BindingResult bindingResult = e.getBindingResult();
        return getBindResultDTO(bindingResult);
    }

    private Result getBindResultDTO(BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (log.isDebugEnabled()) {
            for (FieldError error : fieldErrors) {
                log.error("{} -> {}", error.getDefaultMessage(), error.getDefaultMessage());
            }
        }

        if (fieldErrors.isEmpty()) {
            log.error("validExceptionHandler error fieldErrors is empty");
            Result.ofFail(CommonErrorCode.BUSINESS_ERROR.getCode(), "");
        }

        return Result
                .ofFail(CommonErrorCode.PARAM_ERROR.getCode(), fieldErrors.get(0).getDefaultMessage());
    }

    public void outPutError(Class errorType, Enum secondaryErrorType, Throwable throwable) {
        log.error("[{}] {}: {}", errorType.getSimpleName(), secondaryErrorType, throwable.getMessage(),
                throwable);
    }

    public void outPutErrorWarn(Class errorType, Enum secondaryErrorType, Throwable throwable) {
        log.warn("[{}] {}: {}", errorType.getSimpleName(), secondaryErrorType, throwable.getMessage());
    }

}
