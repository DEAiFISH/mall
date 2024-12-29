package com.deaifish.mall.hanlder;

import com.deaifish.mall.exception.MallException;
import com.deaifish.mall.response.R;
import com.deaifish.mall.response.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.ArrayList;
import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/14 14:25
 */
@RestControllerAdvice
@Slf4j
public class DefaultExceptionHandlerConfig {

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<R<List<String>>> methodArgumentNotValidExceptionHandler(Exception e) {
        log.error("methodArgumentNotValidExceptionHandler", e);
        List<FieldError> fieldErrors = null;
        if (e instanceof MethodArgumentNotValidException) {
            fieldErrors = ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors();
        }
        if (e instanceof BindException) {
            fieldErrors = ((BindException) e).getBindingResult().getFieldErrors();
        }
        if (fieldErrors == null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(R.fail(ResponseEnum.METHOD_ARGUMENT_NOT_VALID));
        }

        List<String> defaultMessages = new ArrayList<>(fieldErrors.size());
        for (FieldError fieldError : fieldErrors) {
            defaultMessages.add(fieldError.getField() + ":" + fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(R.fail(ResponseEnum.METHOD_ARGUMENT_NOT_VALID, defaultMessages));
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<R<List<FieldError>>> methodArgumentNotValidExceptionHandler(
            HttpMessageNotReadableException e) {
        log.error("methodArgumentNotValidExceptionHandler", e);
        return ResponseEntity.status(HttpStatus.OK)
                .body(R.fail(ResponseEnum.HTTP_MESSAGE_NOT_READABLE));
    }

    @ExceptionHandler(MallException.class)
    public ResponseEntity<R<Object>> mall4cloudExceptionHandler(MallException e) {
        log.error("mall4cloudExceptionHandler", e);

        ResponseEnum responseEnum = e.getResponseEnum();
        // 失败返回失败消息 + 状态码
        if (responseEnum != null) {
            return ResponseEntity.status(HttpStatus.OK).body(R.fail(responseEnum, e.getObject()));
        }
        // 失败返回消息 状态码固定为直接显示消息的状态码
        return ResponseEntity.status(HttpStatus.OK).body(R.showFailMsg(e.getMessage()));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<R<String>> handleMaxUploadSizeExceeded(MaxUploadSizeExceededException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(R.fail(ResponseEnum.SHOW_FAIL, "上传文件大小超过限制:" + ex.getMaxUploadSize() / 1024 / 1024 + "MB"));
    }
}
