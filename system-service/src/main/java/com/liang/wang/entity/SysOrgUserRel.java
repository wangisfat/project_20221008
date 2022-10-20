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
@TableName("sys_org_user_rel")
@ApiModel(value = "SysOrgUserRel对象", description = "")
public class SysOrgUserRel implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private Long orgId;

    private Long userId;

    private Integer displayOrder;

      @ApiModelProperty("0-挂职部门，1-主职部门")
      private Boolean isMainOrg;

    
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
    
    public Long getOrgId() {
        return orgId;
    }

      public void setOrgId(Long orgId) {
          this.orgId = orgId;
      }
    
    public Long getUserId() {
        return userId;
    }

      public void setUserId(Long userId) {
          this.userId = userId;
      }
    
    public Integer getDisplayOrder() {
        return displayOrder;
    }

      public void setDisplayOrder(Integer displayOrder) {
          this.displayOrder = displayOrder;
      }
    
    public Boolean getIsMainOrg() {
        return isMainOrg;
    }

      public void setIsMainOrg(Boolean isMainOrg) {
          this.isMainOrg = isMainOrg;
      }

    @Override
    public String toString() {
        return "SysOrgUserRel{" +
              "id=" + id +
                  ", gmtCreate=" + gmtCreate +
                  ", gmtModified=" + gmtModified +
                  ", orgId=" + orgId +
                  ", userId=" + userId +
                  ", displayOrder=" + displayOrder +
                  ", isMainOrg=" + isMainOrg +
              "}";
    }
}
