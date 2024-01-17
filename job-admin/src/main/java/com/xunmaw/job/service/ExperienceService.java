package com.xunmaw.job.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.xunmaw.job.entity.Experience;
import com.xunmaw.job.mapper.ExperienceMapper;

@Service
public class ExperienceService {

    @Autowired
    private ExperienceMapper experienceMapper;

    public int create(Experience experience) {
        return experienceMapper.create(experience);
    }

    public int delete(String ids) {
        String[] arr = ids.split(",");
        int row = 0;
        for (String s : arr) {
            if (!StringUtils.isEmpty(s)) {
                experienceMapper.delete(Integer.parseInt(s));
                row++;
            }
        }
        return row;
    }

    public int delete(Integer id) {
        return experienceMapper.delete(id);
    }

    public int update(Experience experience) {
        return experienceMapper.update(experience);
    }

    public PageInfo<Experience> query(Experience experience) {
        if (experience != null && experience.getPage() != null) {
            PageHelper.startPage(experience.getPage(), experience.getLimit());
        }
        return new PageInfo<>(experienceMapper.query(experience));
    }

    public Experience detail(Integer id) {
        return experienceMapper.detail(id);
    }

    public int count(Experience experience) {
        return experienceMapper.count(experience);
    }
}
