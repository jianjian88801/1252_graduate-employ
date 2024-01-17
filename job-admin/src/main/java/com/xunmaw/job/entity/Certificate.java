package com.xunmaw.job.entity;

import com.xunmaw.job.utils.Entity;
import lombok.Data;

import java.util.Date;

@Data
public class Certificate extends Entity {

	private Integer id;
	private String name;
	private String file;
	private Date obtainDate;
	private Integer resumeId;
	private Resume resume;
	
}
