package com.wdyVBlog.common.utils.poi;

import java.time.LocalDateTime;

public class LocalDateTimeHandler implements ExcelHandlerAdapter{

    @Override
    public Object format(Object value, String[] args) {
        return LocalDateTime.now();
    }

}
