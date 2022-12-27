package com.wdyVBlog.system.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdyVBlog.common.core.page.PageDomain;
import com.wdyVBlog.common.core.page.TableSupport;
import com.wdyVBlog.common.utils.PageResult;
import com.wdyVBlog.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import com.wdyVBlog.common.core.domain.entity.SysDictData;
import com.wdyVBlog.common.utils.DictUtils;
import com.wdyVBlog.system.mapper.SysDictDataMapper;
import com.wdyVBlog.system.service.ISysDictDataService;

/**
 * 字典 业务层处理
 * 
 * @author wdy
 */
@Service
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper,SysDictData> implements ISysDictDataService
{

    /**
     * 根据条件分页查询字典数据
     * 
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataList(SysDictData dictData)
    {
        return this.baseMapper.selectDictDataList(dictData);
    }

    @Override
    public PageResult<SysDictData> selectDictDataPage(SysDictData dictData) {
        LambdaQueryWrapper<SysDictData> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(SysDictData::getDictCode,SysDictData::getDictSort,SysDictData::getDictLabel,SysDictData::getDictValue,SysDictData::getDictType,SysDictData::getCssClass,SysDictData::getListClass,SysDictData::getIsDefault,SysDictData::getStatus)
                .eq(StringUtils.isNotNull(dictData.getDictType()),SysDictData::getDictType,dictData.getDictType())
                .like(StringUtils.isNotNull(dictData.getDictLabel()),SysDictData::getDictLabel,dictData.getDictLabel())
                .eq(StringUtils.isNotNull(dictData.getStatus()),SysDictData::getStatus,dictData.getStatus())
                .orderByAsc(SysDictData::getDictSort);
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Page<SysDictData> sysDictDataPage = new Page<>(pageDomain.getPageNum(), pageDomain.getPageSize());
        Page<SysDictData> page = this.page(sysDictDataPage, wrapper);
        return new PageResult<SysDictData>(page);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     * 
     * @param dictType 字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    @Override
    public String selectDictLabel(String dictType, String dictValue)
    {
        return this.baseMapper.selectDictLabel(dictType, dictValue);
    }

    /**
     * 根据字典数据ID查询信息
     * 
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    @Override
    public SysDictData selectDictDataById(Long dictCode)
    {
        return this.baseMapper.selectDictDataById(dictCode);
    }

    /**
     * 批量删除字典数据信息
     * 
     * @param dictCodes 需要删除的字典数据ID
     */
    @Override
    public void deleteDictDataByIds(Long[] dictCodes)
    {
        for (Long dictCode : dictCodes)
        {
            SysDictData data = selectDictDataById(dictCode);
            this.baseMapper.deleteDictDataById(dictCode);
            List<SysDictData> dictDatas = this.baseMapper.selectDictDataByType(data.getDictType());
            DictUtils.setDictCache(data.getDictType(), dictDatas);
        }
    }

    /**
     * 新增保存字典数据信息
     * 
     * @param data 字典数据信息
     * @return 结果
     */
    @Override
    public int insertDictData(SysDictData data)
    {
        int row = this.baseMapper.insertDictData(data);
        if (row > 0)
        {
            List<SysDictData> dictDatas = this.baseMapper.selectDictDataByType(data.getDictType());
            DictUtils.setDictCache(data.getDictType(), dictDatas);
        }
        return row;
    }

    /**
     * 修改保存字典数据信息
     * 
     * @param data 字典数据信息
     * @return 结果
     */
    @Override
    public int updateDictData(SysDictData data)
    {
        int row = this.baseMapper.updateDictData(data);
        if (row > 0)
        {
            List<SysDictData> dictDatas = this.baseMapper.selectDictDataByType(data.getDictType());
            DictUtils.setDictCache(data.getDictType(), dictDatas);
        }
        return row;
    }
}
