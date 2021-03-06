package cn.ekgc.itrip.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 *<b>爱旅行-标签字典信息实体类</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@ApiModel(value = "字典实体类 LabelDic",description = "名称")
public class LabelDic implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "主键")
	private Long id;                    //主键
	@ApiModelProperty(value = "标签名称")
	private String name;                //标签名key
	@ApiModelProperty(value = "value值")
	private String value;               //value值
	@ApiModelProperty(value = "描述")
	private String description;         //描述
	@ApiModelProperty(value = "父级")
	private Long parentId;              //父级
	@ApiModelProperty(value = "酒店Id")
	private Long hotelId;               //酒店ID
	@ApiModelProperty(value = "标签图标")
	private String pic;                 //标签图片地址
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date creationDate;
	private Long createdBy;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date modifyDate;
	private Long modifiedBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
