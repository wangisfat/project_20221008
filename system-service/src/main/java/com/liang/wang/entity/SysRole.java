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
@TableName("sys_role")
@ApiModel(value = "SysRole对象", description = "")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private String roleCode;

    private String roleName;

      @ApiModelProperty("01: 管理角色 02：业务角色")
      private String roleType;

    private String remark;

    private Boolean isDeleted;

      @ApiModelProperty("是否层级角色")
      private Boolean scopeType;

    
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
    
    public String getRoleCode() {
        return roleCode;
    }

      public void setRoleCode(String roleCode) {
          this.roleCode = roleCode;
      }
    
    public String getRoleName() {
        return roleName;
    }

      public void setRoleName(String roleName) {
          this.roleName = roleName;
      }
    
    public String getRoleType() {
        return roleType;
    }

      public void setRoleType(String roleType) {
          this.roleType = roleType;
      }
    
    public String getRemark() {
        return remark;
    }

      public void setRemark(String remark) {
          this.remark = remark;
      }
    
    public Boolean getIsDeleted() {
        return isDeleted;
    }

      public void setIsDeleted(Boolean isDeleted) {
          this.isDeleted = isDeleted;
      }
    
    public Boolean getScopeType() {
        return scopeType;
    }

      public void setScopeType(Boolean scopeType) {
          this.scopeType = scopeType;
      }

    @Override
    public String toString() {
        return "SysRole{" +
              "id=" + id +
                  ", gmtCreate=" + gmtCreate +
                  ", gmtModified=" + gmtModified +
                  ", roleCode=" + roleCode +
                  ", roleName=" + roleName +
                  ", roleType=" + roleType +
                  ", remark=" + remark +
                  ", isDeleted=" + isDeleted +
                  ", scopeType=" + scopeType +
              "}";
    }
}
