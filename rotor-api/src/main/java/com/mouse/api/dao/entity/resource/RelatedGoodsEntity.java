package com.mouse.api.dao.entity.resource;

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
@Table(name = "tbl_related_goods")
public class RelatedGoodsEntity implements Serializable {

	private static final long serialVersionUID = 6271053767794717250L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "goods_id", columnDefinition = "int unsigned default '0' COMMENT '商品Id'", nullable = false)
	private Integer goodsId;
	@Column(name = "related_goods_id", columnDefinition = "int unsigned default '0' COMMENT '关联商品id'", nullable = false)
	private Integer relatedGoodsId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@CreationTimestamp
	@Column(name = "create_date")
	public Date createDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@UpdateTimestamp
	@Column(name = "update_date")
	public Date updateDate;
}
