package com.mouse.core.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 自定义异常
 * @Date 2019-11-30
 */
@Getter
@Setter
public class BusinessException extends RuntimeException implements Serializable {


    private Integer code;
    private String msg;


    public BusinessException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(String msg) {
        super(msg);
        this.code = BusinessCode.ERROR.getCode();
        this.msg = msg;
    }

    public BusinessException(BusinessCode error) {
        super(error.getDesc());
        this.code = error.getCode();
        this.msg = error.getDesc();
    }


}
