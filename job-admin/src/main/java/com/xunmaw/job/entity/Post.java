package com.xunmaw.job.entity;

import lombok.Data;
import com.xunmaw.job.utils.Entity;

@Data
public class Post extends Entity {

	private Integer id;
	private String name;
	private String education;
	private String salary;
	private String description;
	private Integer companyId;
}
