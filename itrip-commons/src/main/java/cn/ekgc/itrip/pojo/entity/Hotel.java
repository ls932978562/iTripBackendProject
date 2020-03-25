package cn.ekgc.itrip.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>爱旅行-酒店信息实体类</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
public class Hotel implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;                        //主键
	private String hotelName;               //酒店名称
	private Long countryId;                 //国家ID
	private Long provinceId;                //省份ID
	private Long cityId;                    //城市ID
	private String address;                 //酒店地址
	private String details;                 //酒店细节介绍
	private String facilities;              //酒店设施
	private String hotelPolicy;             //酒店政策
	private Integer hotelType;              //酒店类型 1-国内 2-国际
	private Integer hotelLevel;             //酒店级别 1-经济酒店 2-二星 3-三星 4-四星 5-五星
	private Integer isGroupPurchase;        //是否是团购酒店
	private String redundantCityName;       //城市名称冗余字段
	private String redundantProvinceName;   //省名称冗余字段
	private String redundantCountryName;    //城市名称冗余字段
	private Integer redundantHotelStore;    //酒店库存
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

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getFacilities() {
		return facilities;
	}

	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}

	public String getHotelPolicy() {
		return hotelPolicy;
	}

	public void setHotelPolicy(String hotelPolicy) {
		this.hotelPolicy = hotelPolicy;
	}

	public Integer getHotelType() {
		return hotelType;
	}

	public void setHotelType(Integer hotelType) {
		this.hotelType = hotelType;
	}

	public Integer getHotelLevel() {
		return hotelLevel;
	}

	public void setHotelLevel(Integer hotelLevel) {
		this.hotelLevel = hotelLevel;
	}

	public Integer getIsGroupPurchase() {
		return isGroupPurchase;
	}

	public void setIsGroupPurchase(Integer isGroupPurchase) {
		this.isGroupPurchase = isGroupPurchase;
	}

	public String getRedundantCityName() {
		return redundantCityName;
	}

	public void setRedundantCityName(String redundantCityName) {
		this.redundantCityName = redundantCityName;
	}

	public String getRedundantProvinceName() {
		return redundantProvinceName;
	}

	public void setRedundantProvinceName(String redundantProvinceName) {
		this.redundantProvinceName = redundantProvinceName;
	}

	public String getRedundantCountryName() {
		return redundantCountryName;
	}

	public void setRedundantCountryName(String redundantCountryName) {
		this.redundantCountryName = redundantCountryName;
	}

	public Integer getRedundantHotelStore() {
		return redundantHotelStore;
	}

	public void setRedundantHotelStore(Integer redundantHotelStore) {
		this.redundantHotelStore = redundantHotelStore;
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
