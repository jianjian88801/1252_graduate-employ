package com.xunmaw.job.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.xunmaw.job.entity.Certificate;
import com.xunmaw.job.mapper.CertificateMapper;

@Service
public class CertificateService {

    @Autowired
    private CertificateMapper certificateMapper;

    public int create(Certificate certificate) {
        return certificateMapper.create(certificate);
    }

    public int delete(String ids) {
        String[] arr = ids.split(",");
        int row = 0;
        for (String s : arr) {
            if (!StringUtils.isEmpty(s)) {
                certificateMapper.delete(Integer.parseInt(s));
                row++;
            }
        }
        return row;
    }

    public int delete(Integer id) {
        return certificateMapper.delete(id);
    }

    public int update(Certificate certificate) {
        return certificateMapper.update(certificate);
    }

    public PageInfo<Certificate> query(Certificate certificate) {
        if (certificate != null && certificate.getPage() != null) {
            PageHelper.startPage(certificate.getPage(), certificate.getLimit());
        }
        return new PageInfo<>(certificateMapper.query(certificate));
    }

    public Certificate detail(Integer id) {
        return certificateMapper.detail(id);
    }

    public int count(Certificate certificate) {
        return certificateMapper.count(certificate);
    }
}
