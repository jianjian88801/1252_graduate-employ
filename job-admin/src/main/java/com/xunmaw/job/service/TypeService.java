package com.xunmaw.job.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.xunmaw.job.entity.Type;
import com.xunmaw.job.mapper.TypeMapper;

@Service
public class TypeService {

    @Autowired
    private TypeMapper typeMapper;

    public int create(Type type) {
        return typeMapper.create(type);
    }

    public int delete(String ids) {
        String[] arr = ids.split(",");
        int row = 0;
        for (String s : arr) {
            if (!StringUtils.isEmpty(s)) {
                typeMapper.delete(Integer.parseInt(s));
                row++;
            }
        }
        return row;
    }

    public int delete(Integer id) {
        return typeMapper.delete(id);
    }

    public int update(Type type) {
        return typeMapper.update(type);
    }

    public PageInfo<Type> query(Type type) {
        if (type != null && type.getPage() != null) {
            PageHelper.startPage(type.getPage(), type.getLimit());
        }
        return new PageInfo<>(typeMapper.query(type));
    }

    public Type detail(Integer id) {
        return typeMapper.detail(id);
    }

    public int count(Type type) {
        return typeMapper.count(type);
    }
}
