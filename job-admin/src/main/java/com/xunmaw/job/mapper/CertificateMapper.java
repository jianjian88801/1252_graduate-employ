package com.xunmaw.job.mapper;

import java.util.List;

import com.xunmaw.job.entity.Certificate;

public interface CertificateMapper {

	int create(Certificate certificate);

	int delete(Integer id);

	int update(Certificate certificate);

	List<Certificate> query(Certificate certificate);

	Certificate detail(Integer id);

	int count(Certificate certificate);
}
