package com.liang.wang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author wl
 * @since 2022-10-20
 */
@TableName("sys_org")
@ApiModel(value = "SysOrg对象", description = "")
public class SysOrg implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private String orgName;

      @ApiModelProperty("01: 机构/单位 02：部门 03：组织节点")
      private String orgType;

    private Long parentId;

    private Integer displayOrder;

      @ApiModelProperty("组织唯一标识，来源外部系统")
      private String orgSourceId;

    private Boolean isEnabled;

    private Boolean isDeleted;

      @ApiModelProperty("数据来源类型")
      private Boolean orgSourceType;

    
    public Long getId() {
        return id;
    }

      public void setId(Long id) {
          this.id = id;
      }
    
    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

      public void setGmtCreate(LocalDateTime gmtCreate) {
          this.gmtCreate = gmtCreate;
      }
    
    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

      public void setGmtModified(LocalDateTime gmtModified) {
          this.gmtModified = gmtModified;
      }
    
    public String getOrgName() {
        return orgName;
    }

      public void setOrgName(String orgName) {
          this.orgName = orgName;
      }
    
    public String getOrgType() {
        return orgType;
    }

      public void setOrgType(String orgType) {
          this.orgType = orgType;
      }
    
    public Long getParentId() {
        return parentId;
    }

      public void setParentId(Long parentId) {
          this.parentId = parentId;
      }
    
    public Integer getDisplayOrder() {
        return displayOrder;
    }

      public void setDisplayOrder(Integer displayOrder) {
          this.displayOrder = displayOrder;
      }
    
    public String getOrgSourceId() {
        return orgSourceId;
    }

      public void setOrgSourceId(String orgSourceId) {
          this.orgSourceId = orgSourceId;
      }
    
    public Boolean getIsEnabled() {
        return isEnabled;
    }

      public void setIsEnabled(Boolean isEnabled) {
          this.isEnabled = isEnabled;
      }
    
    public Boolean getIsDeleted() {
        return isDeleted;
    }

      public void setIsDeleted(Boolean isDeleted) {
          this.isDeleted = isDeleted;
      }
    
    public Boolean getOrgSourceType() {
        return orgSourceType;
    }

      public void setOrgSourceType(Boolean orgSourceType) {
          this.orgSourceType = orgSourceType;
      }

    @Override
    public String toString() {
        return "SysOrg{" +
              "id=" + id +
                  ", gmtCreate=" + gmtCreate +
                  ", gmtModified=" + gmtModified +
                  ", orgName=" + orgName +
                  ", orgType=" + orgType +
                  ", parentId=" + parentId +
                  ", displayOrder=" + displayOrder +
                  ", orgSourceId=" + orgSourceId +
                  ", isEnabled=" + isEnabled +
                  ", isDeleted=" + isDeleted +
                  ", orgSourceType=" + orgSourceType +
              "}";
    }
}
