package cn.ekgc.itrip.pojo.vo;

import cn.ekgc.itrip.pojo.entity.ItripUserLinkUser;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

import java.util.Date;
import java.util.List;

/**
 * 前端输入-生成订单VO
 * Created by donghai on 2017/5/16.
 */
@ApiModel(value = "ItripAddHotelOrderVO",description = "生成订单VO")
public class ItripAddHotelOrderVO {
    private Long id;
    private Integer orderType;
    private Long hotelId;
    private String hotelName;
    private Long roomId;
    private Integer count;
    private Date checkInDate;
    private Date checkOutDate;
    private String noticePhone;
    private String noticeEmail;
    private String specialRequirement;
    private Integer isNeedInvoice;
    private Integer invoiceType;
    private String invoiceHead;
   /* @ApiModelProperty("[必填] 入住人id，多个id之间用逗号隔开")
    private String linkUserIds;
    @ApiModelProperty("[必填] 入住人名称，多个名称之间用逗号隔开")
    private String linkUserName;*/

    private List<ItripUserLinkUser> linkUser;       //只传递：id、linkUserName

    public List<ItripUserLinkUser> getLinkUser() {
        return linkUser;
    }

    public void setLinkUser(List<ItripUserLinkUser> linkUser) {
        this.linkUser = linkUser;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getNoticePhone() {
        return noticePhone;
    }

    public void setNoticePhone(String noticePhone) {
        this.noticePhone = noticePhone;
    }

    public String getNoticeEmail() {
        return noticeEmail;
    }

    public void setNoticeEmail(String noticeEmail) {
        this.noticeEmail = noticeEmail;
    }

    public String getSpecialRequirement() {
        return specialRequirement;
    }

    public void setSpecialRequirement(String specialRequirement) {
        this.specialRequirement = specialRequirement;
    }

    public Integer getIsNeedInvoice() {
        return isNeedInvoice;
    }

    public void setIsNeedInvoice(Integer isNeedInvoice) {
        this.isNeedInvoice = isNeedInvoice;
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceHead() {
        return invoiceHead;
    }

    public void setInvoiceHead(String invoiceHead) {
        this.invoiceHead = invoiceHead;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
