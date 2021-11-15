package com.baiyao.identity.handle;

import com.baiyao.identity.config.ErrorCode;
import com.baiyao.identity.to.ErrorResultTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baiyao
 * @date 2021/10/28 15:30
 * @description
 */
@Slf4j
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {


    /**
     * controller 请求参数校验结果
     *
     * @param e 参数
     * @return ResponseEntity<?>
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validExceptionHandler(MethodArgumentNotValidException e) {
        List<String> errorMessage = new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            if (!errorMessage.contains(fieldError.getDefaultMessage())) {
                errorMessage.add(fieldError.getDefaultMessage());
            }
        });
        return ResponseEntity.status(ErrorCode.EMPTY_REQUEST_BODY.getResponseStatus())
                .body(ErrorResultTO.createFailInstance(ErrorCode.EMPTY_REQUEST_BODY.getErrorCode(),
                        StringUtils.join(errorMessage, ", ")));
    }
}
