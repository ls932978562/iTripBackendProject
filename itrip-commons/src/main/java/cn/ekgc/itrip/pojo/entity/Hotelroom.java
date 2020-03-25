package cn.ekgc.itrip.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
/**
 *<b>爱旅行-酒店房间信息实体类</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
public class Hotelroom implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long hotelId;
	private String roomTitle;                   //房间名称
	private Double roomPrice;                   //房间价格
	private Long roomBedTypeId;                 //酒店床型
	private Integer isHavingBreakfast;          //早餐
	private Integer payType;                    //支付方式  1-在线 2-到店 3-任意
	private Double satisfaction;                //是否满意
	private Integer isBook;                     //是否可预订 0-不可以 1-可以
	private Integer isCancel;                   //是否可取消 0-不可以 1-可以
	private Integer isTimelyResponse;           //是否及时响应 0-不可以 1-可以
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

	public String getRoomTitle() {
		return roomTitle;
	}

	public void setRoomTitle(String roomTitle) {
		this.roomTitle = roomTitle;
	}

	public Double getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(Double roomPrice) {
		this.roomPrice = roomPrice;
	}

	public Long getRoomBedTypeId() {
		return roomBedTypeId;
	}

	public void setRoomBedTypeId(Long roomBedTypeId) {
		this.roomBedTypeId = roomBedTypeId;
	}

	public Integer getIsHavingBreakfast() {
		return isHavingBreakfast;
	}

	public void setIsHavingBreakfast(Integer isHavingBreakfast) {
		this.isHavingBreakfast = isHavingBreakfast;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Double getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(Double satisfaction) {
		this.satisfaction = satisfaction;
	}

	public Integer getIsBook() {
		return isBook;
	}

	public void setIsBook(Integer isBook) {
		this.isBook = isBook;
	}

	public Integer getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(Integer isCancel) {
		this.isCancel = isCancel;
	}

	public Integer getIsTimelyResponse() {
		return isTimelyResponse;
	}

	public void setIsTimelyResponse(Integer isTimelyResponse) {
		this.isTimelyResponse = isTimelyResponse;
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
