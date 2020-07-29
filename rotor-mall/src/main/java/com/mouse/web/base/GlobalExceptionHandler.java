package com.mouse.web.base;

import com.mouse.core.base.BusinessCode;
import com.mouse.core.base.BusinessException;
import com.mouse.core.base.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * @author ; lidongdong
 * @Description 全局异常处理类
 * @Date 2019-12-13
 */
@Slf4j
@Component
public class GlobalExceptionHandler extends BaseController {


    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public R exceptionHandler(BusinessException e) {
        log.error("BusinessException[业务异常]", e.getMessage(), e);
        return new R(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R exceptionHandler(Exception e) {
        log.error("SystemException[系统异常]", e.getMessage(), e);
        return R.error();
    }


    /**
     * hibernate 参数校验出错会抛出 ConstraintViolationException 异常
     * 在此方法中处理，将错误信息输出
     *
     * @param e
     * @return
     */
    @ExceptionHandler
    @ResponseBody

    public Object exceptionHandler(ValidationException e) {
        log.error("ValidationException,e:" + e.getMessage(), e);
        StringBuilder errorInfo = new StringBuilder("");
        if (e instanceof ConstraintViolationException) {
            ConstraintViolationException exs = (ConstraintViolationException) e;
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();

            for (ConstraintViolation<?> item : violations) {
                errorInfo.append(item.getMessage());
            }
        }
        return new R(BusinessCode.ERROR_SYS_PARAMS.getCode(), errorInfo.toString());
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public R exceptionHandler(BindException e) {
        log.error("BindException[绑定异常],e:" + e.getMessage(), e);
        return new R(BusinessCode.ERROR_SYS_PARAMS.getCode(), e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 校验错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public R exceptionHandler(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException[校验错误]", e);
        return new R(BusinessCode.ERROR_SYS_PARAMS.getCode(), e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public R exceptionHandler(MissingServletRequestParameterException e) {
        log.error("MissingServletRequestParameterException[缺少参数]", e);
        return new R(BusinessCode.ERROR_SYS_PARAMS.getCode(), "请求参数 " + e.getParameterName() + " 不能为空");
    }

    /**
     * 参数类型转换错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseBody
    public R exceptionHandler(HttpMessageConversionException e) {
        log.error("HttpMessageConversionException[参数类型转换错误]", e);
        return new R(BusinessCode.ERROR_SYS_PARAMS.getCode(), "参数格式错误");
    }

}
