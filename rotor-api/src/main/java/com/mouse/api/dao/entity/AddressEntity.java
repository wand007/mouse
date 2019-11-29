package com.mouse.api.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-26
 */
@Data
@Entity
@Table(name = "tbl_address")
public class AddressEntity implements Serializable {

    private static final long serialVersionUID = 6234198184638503274L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id", columnDefinition = "int COMMENT '会员ID'", nullable = false)
    private Integer userId;
    @Column(name = "user_name", columnDefinition = "varchar(32) COMMENT '收货人姓名'", nullable = false)
    private String userName;
    @Column(name = "tel_number", columnDefinition = "varchar(32) COMMENT '手机'", nullable = false)
    private String telNumber;
    @Column(name = "postal_code", columnDefinition = "varchar(32) COMMENT '邮编'", nullable = false)
    private String postalCode;
    @Column(name = "national_code", columnDefinition = "varchar(32) COMMENT '收货地址国家码'", nullable = false)
    private String nationalCode;
    @Column(name = "province_name", columnDefinition = "varchar(32) COMMENT '省'", nullable = false)
    private String provinceName;
    @Column(name = "city_name", columnDefinition = "varchar(32) COMMENT '市'", nullable = false)
    private String cityName;
    @Column(name = "county_name", columnDefinition = "varchar(32) COMMENT '区'", nullable = false)
    private String countyName;
    @Column(name = "address", columnDefinition = "varchar(32) COMMENT '收货地址信息'", nullable = false)
    private String address;
    @Column(name = "is_default", columnDefinition = "tinyint unsigned DEFAULT '1' COMMENT '默认'", nullable = false)
    private Boolean isDefault;
    //    //入参时，请求报文只需要传入yyyymmddhhmmss字符串进来，则自动转换为Date类型数据
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    //出参时间格式化
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    @Column(name = "create_date")
    public Date createDate;
    //出参时间格式化
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    @Column(name = "update_date")
    public Date updateDate;


}
