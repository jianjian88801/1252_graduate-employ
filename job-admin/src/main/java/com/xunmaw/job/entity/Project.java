package com.xunmaw.job.entity;

import lombok.Data;
import com.xunmaw.job.utils.Entity;
import java.util.Date;

@Data
public class Project extends Entity {

	private Integer id;
	private String name;
	private String description;
	private Date startDate;
	private Date endDate;
	private Integer resumeId;

	private Resume resume;
}
