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
@TableName("sys_user")
@ApiModel(value = "SysUser对象", description = "")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

      @ApiModelProperty("姓名")
      private String userName;

      @ApiModelProperty("账号名")
      private String loginName;

      @ApiModelProperty("初始密码")
      private String initialPassword;

      @ApiModelProperty("登陆密码")
      private String loginPassword;

      @ApiModelProperty("最后一次修改密码时间")
      private LocalDateTime lastChangePasswordTime;

      @ApiModelProperty("手机号")
      private String mobile;

      @ApiModelProperty("排序号")
      private Integer displayOrder;

      @ApiModelProperty("邮箱")
      private String email;

      @ApiModelProperty("头像URL")
      private String avatar;

      @ApiModelProperty("备注")
      private String remark;

      @ApiModelProperty("是否启用")
      private Boolean isEnabled;

      @ApiModelProperty("是否删除")
      private Boolean isDeleted;

      @ApiModelProperty("用户来源")
      private String userSourceType;

      @ApiModelProperty("外部来源用户id")
      private String userSourceId;

      @ApiModelProperty("用户等级")
      private String userLevel;

      @ApiModelProperty("人员状态（01-未激活 02-已锁定 03-正常）")
      private String userStatus;

      @ApiModelProperty("解锁时间")
      private LocalDateTime unlockTime;

    
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
    
    public String getUserName() {
        return userName;
    }

      public void setUserName(String userName) {
          this.userName = userName;
      }
    
    public String getLoginName() {
        return loginName;
    }

      public void setLoginName(String loginName) {
          this.loginName = loginName;
      }
    
    public String getInitialPassword() {
        return initialPassword;
    }

      public void setInitialPassword(String initialPassword) {
          this.initialPassword = initialPassword;
      }
    
    public String getLoginPassword() {
        return loginPassword;
    }

      public void setLoginPassword(String loginPassword) {
          this.loginPassword = loginPassword;
      }
    
    public LocalDateTime getLastChangePasswordTime() {
        return lastChangePasswordTime;
    }

      public void setLastChangePasswordTime(LocalDateTime lastChangePasswordTime) {
          this.lastChangePasswordTime = lastChangePasswordTime;
      }
    
    public String getMobile() {
        return mobile;
    }

      public void setMobile(String mobile) {
          this.mobile = mobile;
      }
    
    public Integer getDisplayOrder() {
        return displayOrder;
    }

      public void setDisplayOrder(Integer displayOrder) {
          this.displayOrder = displayOrder;
      }
    
    public String getEmail() {
        return email;
    }

      public void setEmail(String email) {
          this.email = email;
      }
    
    public String getAvatar() {
        return avatar;
    }

      public void setAvatar(String avatar) {
          this.avatar = avatar;
      }
    
    public String getRemark() {
        return remark;
    }

      public void setRemark(String remark) {
          this.remark = remark;
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
    
    public String getUserSourceType() {
        return userSourceType;
    }

      public void setUserSourceType(String userSourceType) {
          this.userSourceType = userSourceType;
      }
    
    public String getUserSourceId() {
        return userSourceId;
    }

      public void setUserSourceId(String userSourceId) {
          this.userSourceId = userSourceId;
      }
    
    public String getUserLevel() {
        return userLevel;
    }

      public void setUserLevel(String userLevel) {
          this.userLevel = userLevel;
      }
    
    public String getUserStatus() {
        return userStatus;
    }

      public void setUserStatus(String userStatus) {
          this.userStatus = userStatus;
      }
    
    public LocalDateTime getUnlockTime() {
        return unlockTime;
    }

      public void setUnlockTime(LocalDateTime unlockTime) {
          this.unlockTime = unlockTime;
      }

    @Override
    public String toString() {
        return "SysUser{" +
              "id=" + id +
                  ", gmtCreate=" + gmtCreate +
                  ", gmtModified=" + gmtModified +
                  ", userName=" + userName +
                  ", loginName=" + loginName +
                  ", initialPassword=" + initialPassword +
                  ", loginPassword=" + loginPassword +
                  ", lastChangePasswordTime=" + lastChangePasswordTime +
                  ", mobile=" + mobile +
                  ", displayOrder=" + displayOrder +
                  ", email=" + email +
                  ", avatar=" + avatar +
                  ", remark=" + remark +
                  ", isEnabled=" + isEnabled +
                  ", isDeleted=" + isDeleted +
                  ", userSourceType=" + userSourceType +
                  ", userSourceId=" + userSourceId +
                  ", userLevel=" + userLevel +
                  ", userStatus=" + userStatus +
                  ", unlockTime=" + unlockTime +
              "}";
    }
}
