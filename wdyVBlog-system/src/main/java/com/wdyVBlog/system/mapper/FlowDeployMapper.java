package com.wdyVBlog.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wdyVBlog.system.dto.FlowProcDefDto;
import org.apache.ibatis.annotations.Param;

/**
 * 流程定义查询
 *
 * @author Xuan Xuan
 * @email
 * @date 2022/1/29 5:44 下午
 **/
public interface FlowDeployMapper {

    /**
     * 流程定义列表
     * @param name
     * @return
     */
    Page<FlowProcDefDto> selectDeployList(Page<FlowProcDefDto> dtoPage,@Param("name") String name);
}
