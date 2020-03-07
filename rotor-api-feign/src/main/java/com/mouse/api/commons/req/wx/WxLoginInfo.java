package com.mouse.api.commons.req.wx;

import com.mouse.core.params.req.UserInfo;
import lombok.Data;

import java.io.Serializable;

@Data
public class WxLoginInfo implements Serializable {
    private static final long serialVersionUID = 1340639900860402669L;

    private String code;

    private UserInfo userInfo;

}
