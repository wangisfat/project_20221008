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
 * 系统数据字典
 * </p>
 *
 * @author wl
 * @since 2022-10-20
 */
@TableName("sys_config")
@ApiModel(value = "SysConfig对象", description = "系统数据字典")
public class SysConfig implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      @ApiModelProperty("创建时间")
      private LocalDateTime gmtCreate;

      @ApiModelProperty("修改时间")
      private LocalDateTime gmtModified;

      @ApiModelProperty("字典编码")
      private String configCode;

      @ApiModelProperty("字典名称")
      private String configName;

    
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
    
    public String getConfigCode() {
        return configCode;
    }

      public void setConfigCode(String configCode) {
          this.configCode = configCode;
      }
    
    public String getConfigName() {
        return configName;
    }

      public void setConfigName(String configName) {
          this.configName = configName;
      }

    @Override
    public String toString() {
        return "SysConfig{" +
              "id=" + id +
                  ", gmtCreate=" + gmtCreate +
                  ", gmtModified=" + gmtModified +
                  ", configCode=" + configCode +
                  ", configName=" + configName +
              "}";
    }
}
