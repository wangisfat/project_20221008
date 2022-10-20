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
 * 系统数据字典配置项
 * </p>
 *
 * @author wl
 * @since 2022-10-20
 */
@TableName("sys_config_item")
@ApiModel(value = "SysConfigItem对象", description = "系统数据字典配置项")
public class SysConfigItem implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      @ApiModelProperty("创建时间")
      private LocalDateTime gmtCreate;

      @ApiModelProperty("修改时间")
      private LocalDateTime gmtModified;

      @ApiModelProperty("配置编码")
      private Long configId;

      @ApiModelProperty("数据字典id")
      private String itemCode;

      @ApiModelProperty("配置项名称")
      private String itemName;

      @ApiModelProperty("配置项描述")
      private String itemValue;

    
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
    
    public Long getConfigId() {
        return configId;
    }

      public void setConfigId(Long configId) {
          this.configId = configId;
      }
    
    public String getItemCode() {
        return itemCode;
    }

      public void setItemCode(String itemCode) {
          this.itemCode = itemCode;
      }
    
    public String getItemName() {
        return itemName;
    }

      public void setItemName(String itemName) {
          this.itemName = itemName;
      }
    
    public String getItemValue() {
        return itemValue;
    }

      public void setItemValue(String itemValue) {
          this.itemValue = itemValue;
      }

    @Override
    public String toString() {
        return "SysConfigItem{" +
              "id=" + id +
                  ", gmtCreate=" + gmtCreate +
                  ", gmtModified=" + gmtModified +
                  ", configId=" + configId +
                  ", itemCode=" + itemCode +
                  ", itemName=" + itemName +
                  ", itemValue=" + itemValue +
              "}";
    }
}
