package com.wdyVBlog.system.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdyVBlog.common.core.domain.BaseEntity;
import com.wdyVBlog.common.core.page.PageDomain;
import com.wdyVBlog.common.core.page.TableSupport;
import com.wdyVBlog.common.utils.PageResult;
import com.wdyVBlog.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import com.wdyVBlog.system.domain.SysNotice;
import com.wdyVBlog.system.mapper.SysNoticeMapper;
import com.wdyVBlog.system.service.ISysNoticeService;

/**
 * 公告 服务层实现
 * 
 * @author wdy
 */
@Service
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper,SysNotice> implements ISysNoticeService
{

    /**
     * 查询公告信息
     * 
     * @param noticeId 公告ID
     * @return 公告信息
     */
    @Override
    public SysNotice selectNoticeById(Long noticeId)
    {
        return this.baseMapper.selectNoticeById(noticeId);
    }

    /**
     * 查询公告列表
     * 
     * @param notice 公告信息
     * @return 公告集合
     */
    @Override
    public List<SysNotice> selectNoticeList(SysNotice notice)
    {
        return this.baseMapper.selectNoticeList(notice);
    }

    @Override
    public PageResult<SysNotice> selectNoticePage(SysNotice notice) {
        LambdaQueryWrapper<SysNotice> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(SysNotice::getNoticeId,SysNotice::getNoticeTitle,SysNotice::getNoticeType,SysNotice::getNoticeContent,SysNotice::getStatus,BaseEntity::getCreateBy,BaseEntity::getCreateTime,BaseEntity::getUpdateBy,BaseEntity::getUpdateTime,BaseEntity::getRemark)
                .like(StringUtils.isNotNull(notice.getNoticeTitle()), SysNotice::getNoticeTitle, notice.getNoticeTitle())
                .eq(StringUtils.isNotNull(notice.getNoticeType()), SysNotice::getNoticeType, notice.getNoticeType())
                .like(StringUtils.isNotNull(notice.getCreateBy()), BaseEntity::getCreateBy, notice.getCreateBy());
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Page<SysNotice> sysNoticePage = new Page<>(pageDomain.getPageNum(), pageDomain.getPageSize());
        Page<SysNotice> page = this.page(sysNoticePage, wrapper);
        return new PageResult<SysNotice>(page);
    }

    /**
     * 新增公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int insertNotice(SysNotice notice)
    {
        return this.baseMapper.insertNotice(notice);
    }

    /**
     * 修改公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int updateNotice(SysNotice notice)
    {
        return this.baseMapper.updateNotice(notice);
    }

    /**
     * 删除公告对象
     * 
     * @param noticeId 公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeById(Long noticeId)
    {
        return this.baseMapper.deleteNoticeById(noticeId);
    }

    /**
     * 批量删除公告信息
     * 
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeByIds(Long[] noticeIds)
    {
        return this.baseMapper.deleteNoticeByIds(noticeIds);
    }
}
