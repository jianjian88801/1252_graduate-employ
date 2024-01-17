package com.xunmaw.job.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.xunmaw.job.entity.Company;
import com.xunmaw.job.mapper.CompanyMapper;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    public int create(Company company) {
        return companyMapper.create(company);
    }

    public int delete(String ids) {
        String[] arr = ids.split(",");
        int row = 0;
        for (String s : arr) {
            if (!StringUtils.isEmpty(s)) {
                companyMapper.delete(Integer.parseInt(s));
                row++;
            }
        }
        return row;
    }

    public int delete(Integer id) {
        return companyMapper.delete(id);
    }

    public int update(Company company) {
        return companyMapper.update(company);
    }

    public PageInfo<Company> query(Company company) {
        if (company != null && company.getPage() != null) {
            PageHelper.startPage(company.getPage(), company.getLimit());
        }
        return new PageInfo<>(companyMapper.query(company));
    }

    public Company detail(Integer id) {
        return companyMapper.detail(id);
    }

    public int count(Company company) {
        return companyMapper.count(company);
    }

    public Company login(String account, String password) {
        Company company = new Company();
        company.setAccount(account);
        company.setPassword(password);
        List<Company> list = companyMapper.query(company);
        if(list != null && list.size() >= 1) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
