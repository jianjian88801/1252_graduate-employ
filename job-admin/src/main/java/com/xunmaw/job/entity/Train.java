package com.xunmaw.job.entity;

import lombok.Data;
import com.xunmaw.job.utils.Entity;
import java.util.Date;

@Data
public class Train extends Entity {

	private Integer id;
	private Date startDate;
	private Date endDate;
	private String company;
	private String course;
	private Integer resumeId;

	private Resume resume;
}
