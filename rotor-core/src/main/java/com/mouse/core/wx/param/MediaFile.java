package com.mouse.core.wx.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ; lidongdong
 * @Description 微信媒体类型类
 * @Date 2019-12-31
 */
@Data
public class MediaFile implements Serializable {
    private static final long serialVersionUID = 6664583044014674848L;
    private String fileName;
    private String fullName;
    private String suffix;
    private String contentLength;
    private String contentType;
    private byte[] fileBytes;
    private String error;


}