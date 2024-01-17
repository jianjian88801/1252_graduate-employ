package com.xunmaw.job.entity;

import lombok.Data;
import com.xunmaw.job.utils.Entity;
import java.util.Date;

@Data
public class Send extends Entity {

	private Integer id;
	private Integer companyId;
	private Integer postId;
	private Integer resumeId;
	private Date sendDate;
	private Integer status; // 0待查看|1已查看|2有意向|3不合适
	private Integer studentId;

	private Company company;
	private Student student;
	private Post post;
	private Resume resume;
}
