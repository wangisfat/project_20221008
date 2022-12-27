package com.wdyVBlog.common.exception.file;

import com.wdyVBlog.common.exception.base.BaseException;

/**
 * 文件信息异常类
 * 
 * @author wdy
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
