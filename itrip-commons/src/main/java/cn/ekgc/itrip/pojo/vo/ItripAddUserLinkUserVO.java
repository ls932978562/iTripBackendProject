package cn.ekgc.itrip.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 前端输入-新增常用联系人VO
 * Created by donghai on 2017/5/10.
 */
public class ItripAddUserLinkUserVO {

    private String linkUserName;
    private Integer linkIdCardType;
    private String linkIdCard;
    private String linkPhone;
    private Long userId;

    public void setLinkUserName (String  linkUserName){
        this.linkUserName=linkUserName;
    }

    public  String getLinkUserName(){
        return this.linkUserName;
    }
    public void setLinkIdCard (String  linkIdCard){
        this.linkIdCard=linkIdCard;
    }

    public Integer getLinkIdCardType() {
        return linkIdCardType;
    }

    public void setLinkIdCardType(Integer linkIdCardType) {
        this.linkIdCardType = linkIdCardType;
    }

    public  String getLinkIdCard(){
        return this.linkIdCard;
    }
    public void setLinkPhone (String  linkPhone){
        this.linkPhone=linkPhone;
    }

    public  String getLinkPhone(){
        return this.linkPhone;
    }
    public void setUserId (Long  userId){
        this.userId=userId;
    }

    public  Long getUserId(){
        return this.userId;
    }
}
