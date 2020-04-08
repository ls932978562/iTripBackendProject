package cn.ekgc.itrip.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>爱旅行-图片信息实体类</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@ApiModel(value = "图片信息ItripImage")
public class ItripImage implements Serializable {
    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "类型")
    private Integer type;
    @ApiModelProperty(value = "targetId")
    private Long targetId;
    @ApiModelProperty(value = "顺序")
    private Integer position;
    @ApiModelProperty(value = "图片地址")
    private String imgUrl;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date creationDate;
    private Long createdBy;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date modifyDate;
    private Long modifiedBy;

    public void setId (Long  id){
        this.id=id;
    }

    public  Long getId(){
        return this.id;
    }
    public void setType (Integer  type){
        this.type=type;
    }

    public  Integer getType(){
        return this.type;
    }
    public void setTargetId (Long  targetId){
        this.targetId=targetId;
    }

    public  Long getTargetId(){
        return this.targetId;
    }
    public void setPosition (Integer  position){
        this.position=position;
    }

    public  Integer getPosition(){
        return this.position;
    }
    public void setImgUrl (String  imgUrl){
        this.imgUrl=imgUrl;
    }

    public  String getImgUrl(){
        return this.imgUrl;
    }
    public void setCreationDate (Date  creationDate){
        this.creationDate=creationDate;
    }

    public  Date getCreationDate(){
        return this.creationDate;
    }
    public void setCreatedBy (Long  createdBy){
        this.createdBy=createdBy;
    }

    public  Long getCreatedBy(){
        return this.createdBy;
    }
    public void setModifyDate (Date  modifyDate){
        this.modifyDate=modifyDate;
    }

    public  Date getModifyDate(){
        return this.modifyDate;
    }
    public void setModifiedBy (Long  modifiedBy){
        this.modifiedBy=modifiedBy;
    }

    public  Long getModifiedBy(){
        return this.modifiedBy;
    }

}
