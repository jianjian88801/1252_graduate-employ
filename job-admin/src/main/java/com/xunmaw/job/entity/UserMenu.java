package com.xunmaw.job.entity;

import lombok.Data;
import com.xunmaw.job.utils.Entity;

@Data
public class UserMenu extends Entity {

	private Integer userId;
	private Integer menuId;
}
