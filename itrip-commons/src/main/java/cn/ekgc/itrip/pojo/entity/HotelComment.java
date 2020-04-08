package cn.ekgc.itrip.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>爱旅行-酒店评论实体类</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@ApiModel(value = "酒店评论HotelComment")
public class HotelComment implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "主键")
	private Long id;
	@ApiModelProperty(value = "酒店Id")
	private Long hotelId;
	@ApiModelProperty(value = "产品Id")
	private Long productId;
	@ApiModelProperty(value = "订单Id")
	private Long orderId;
	@ApiModelProperty(value = "产品类型")
	private Integer productType;            //商品类型 0—旅游 1-酒店产品 2-机票产品
	@ApiModelProperty(value = "评论内容")
	private String content;                 //评论内容
	@ApiModelProperty(value = "用户ID")
	private Long userId;                    //用户编号
	@ApiModelProperty(value = "是否包含图片")
	private Integer isHavingImg;            //是否包含图片
	@ApiModelProperty(value = "位置评分")
	private Integer positionScore;          //位置评分
	@ApiModelProperty(value = "设施评分")
	private Integer facilitiesScore;        //设施评分
	@ApiModelProperty(value = "服务评分")
	private Integer serviceScore;           //服务评分
	@ApiModelProperty(value = "卫生评分")
	private Integer hygieneScore;           //卫生评分
	@ApiModelProperty(value = "综合评分")
	private Integer score;                  //综合评分
	@ApiModelProperty(value = "出游类型")
	private String tripMode;                //出游类型
	@ApiModelProperty(value = "是否满意")
	private Integer isOk;                   //是否满意
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

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getIsHavingImg() {
		return isHavingImg;
	}

	public void setIsHavingImg(Integer isHavingImg) {
		this.isHavingImg = isHavingImg;
	}

	public Integer getPositionScore() {
		return positionScore;
	}

	public void setPositionScore(Integer positionScore) {
		this.positionScore = positionScore;
	}

	public Integer getFacilitiesScore() {
		return facilitiesScore;
	}

	public void setFacilitiesScore(Integer facilitiesScore) {
		this.facilitiesScore = facilitiesScore;
	}

	public Integer getServiceScore() {
		return serviceScore;
	}

	public void setServiceScore(Integer serviceScore) {
		this.serviceScore = serviceScore;
	}

	public Integer getHygieneScore() {
		return hygieneScore;
	}

	public void setHygieneScore(Integer hygieneScore) {
		this.hygieneScore = hygieneScore;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getTripMode() {
		return tripMode;
	}

	public void setTripMode(String tripMode) {
		this.tripMode = tripMode;
	}

	public Integer getIsOk() {
		return isOk;
	}

	public void setIsOk(Integer isOk) {
		this.isOk = isOk;
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
