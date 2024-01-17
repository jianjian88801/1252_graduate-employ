package com.xunmaw.job.entity;

import lombok.Data;
import com.xunmaw.job.utils.Entity;

@Data
public class Dict extends Entity {

	private Integer id;
	private String dictKey;
	private String dictVal;
	private Integer sort;
	private Integer status; // 0正常|1删除
	private Integer typeId;
}
