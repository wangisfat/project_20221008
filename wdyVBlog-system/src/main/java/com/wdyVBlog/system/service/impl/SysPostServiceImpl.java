package com.wdyVBlog.system.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdyVBlog.common.core.domain.BaseEntity;
import com.wdyVBlog.common.core.page.PageDomain;
import com.wdyVBlog.common.core.page.TableSupport;
import com.wdyVBlog.common.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wdyVBlog.common.constant.UserConstants;
import com.wdyVBlog.common.exception.ServiceException;
import com.wdyVBlog.common.utils.StringUtils;
import com.wdyVBlog.system.domain.SysPost;
import com.wdyVBlog.system.mapper.SysPostMapper;
import com.wdyVBlog.system.mapper.SysUserPostMapper;
import com.wdyVBlog.system.service.ISysPostService;

/**
 * 岗位信息 服务层处理
 * 
 * @author wdy
 */
@Service
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper,SysPost> implements ISysPostService
{

    @Autowired
    private SysUserPostMapper userPostMapper;

    /**
     * 查询岗位信息集合
     * 
     * @param post 岗位信息
     * @return 岗位信息集合
     */
    @Override
    public List<SysPost> selectPostList(SysPost post)
    {
        return this.baseMapper.selectPostList(post);
    }

    @Override
    public PageResult<SysPost> selectPostPage(SysPost post) {
        LambdaQueryWrapper<SysPost> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(SysPost::getPostId,SysPost::getPostCode,SysPost::getPostName,SysPost::getPostSort,SysPost::getStatus, BaseEntity::getCreateBy,BaseEntity::getCreateTime,BaseEntity::getRemark)
                .like(StringUtils.isNotNull(post.getPostCode()),SysPost::getPostCode,post.getPostCode())
                .eq(StringUtils.isNotNull(post.getStatus()),SysPost::getStatus,post.getStatus())
                .like(StringUtils.isNotNull(post.getPostName()),SysPost::getPostName,post.getPostName());
        PageDomain pageDomain = TableSupport.getPageDomain();
        Page<SysPost> sysPostPage = new Page<>(pageDomain.getPageNum(), pageDomain.getPageSize());
        Page<SysPost> page = this.page(sysPostPage, wrapper);
        return new PageResult<SysPost>(page);
    }

    /**
     * 查询所有岗位
     * 
     * @return 岗位列表
     */
    @Override
    public List<SysPost> selectPostAll()
    {
        return this.baseMapper.selectPostAll();
    }

    /**
     * 通过岗位ID查询岗位信息
     * 
     * @param postId 岗位ID
     * @return 角色对象信息
     */
    @Override
    public SysPost selectPostById(Long postId)
    {
        return this.baseMapper.selectPostById(postId);
    }

    /**
     * 根据用户ID获取岗位选择框列表
     * 
     * @param userId 用户ID
     * @return 选中岗位ID列表
     */
    @Override
    public List<Long> selectPostListByUserId(Long userId)
    {
        return this.baseMapper.selectPostListByUserId(userId);
    }

    /**
     * 校验岗位名称是否唯一
     * 
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public String checkPostNameUnique(SysPost post)
    {
        Long postId = StringUtils.isNull(post.getPostId()) ? -1L : post.getPostId();
        SysPost info = this.baseMapper.checkPostNameUnique(post.getPostName());
        if (StringUtils.isNotNull(info) && info.getPostId().longValue() != postId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验岗位编码是否唯一
     * 
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public String checkPostCodeUnique(SysPost post)
    {
        Long postId = StringUtils.isNull(post.getPostId()) ? -1L : post.getPostId();
        SysPost info = this.baseMapper.checkPostCodeUnique(post.getPostCode());
        if (StringUtils.isNotNull(info) && info.getPostId().longValue() != postId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 通过岗位ID查询岗位使用数量
     * 
     * @param postId 岗位ID
     * @return 结果
     */
    @Override
    public int countUserPostById(Long postId)
    {
        return userPostMapper.countUserPostById(postId);
    }

    /**
     * 删除岗位信息
     * 
     * @param postId 岗位ID
     * @return 结果
     */
    @Override
    public int deletePostById(Long postId)
    {
        return this.baseMapper.deletePostById(postId);
    }

    /**
     * 批量删除岗位信息
     * 
     * @param postIds 需要删除的岗位ID
     * @return 结果
     */
    @Override
    public int deletePostByIds(Long[] postIds)
    {
        for (Long postId : postIds)
        {
            SysPost post = selectPostById(postId);
            if (countUserPostById(postId) > 0)
            {
                throw new ServiceException(String.format("%1$s已分配,不能删除", post.getPostName()));
            }
        }
        return this.baseMapper.deletePostByIds(postIds);
    }

    /**
     * 新增保存岗位信息
     * 
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public int insertPost(SysPost post)
    {
        return this.baseMapper.insertPost(post);
    }

    /**
     * 修改保存岗位信息
     * 
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public int updatePost(SysPost post)
    {
        return this.baseMapper.updatePost(post);
    }
}
