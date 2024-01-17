package com.xunmaw.job.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xunmaw.job.entity.Intention;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.xunmaw.job.mapper.IntentionMapper;

@Service
public class IntentionService {

    @Autowired
    private IntentionMapper intentionMapper;

    public int create(Intention intention) {
        return intentionMapper.create(intention);
    }

    public int delete(String ids) {
        String[] arr = ids.split(",");
        int row = 0;
        for (String s : arr) {
            if (!StringUtils.isEmpty(s)) {
                intentionMapper.delete(Integer.parseInt(s));
                row++;
            }
        }
        return row;
    }

    public int delete(Integer id) {
        return intentionMapper.delete(id);
    }

    public int update(Intention intention) {
        return intentionMapper.update(intention);
    }

    public PageInfo<Intention> query(Intention intention) {
        if (intention != null && intention.getPage() != null) {
            PageHelper.startPage(intention.getPage(), intention.getLimit());
        }
        return new PageInfo<>(intentionMapper.query(intention));
    }

    public Intention detail(Integer id) {
        return intentionMapper.detail(id);
    }

    public int count(Intention intention) {
        return intentionMapper.count(intention);
    }
}
