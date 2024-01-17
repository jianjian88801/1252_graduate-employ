package com.xunmaw.job.entity;

import com.xunmaw.job.utils.Entity;
import lombok.Data;

@Data
public class Profession extends Entity {

	private Integer id;
	private String name;
	private Integer parentId;
}
