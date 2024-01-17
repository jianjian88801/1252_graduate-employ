package com.xunmaw.job.entity;

import lombok.Data;
import com.xunmaw.job.utils.Entity;
import java.util.Date;

@Data
public class Student extends Entity {

	private Integer id;
	private String name;
	private String account;
	private String password;
	private Date birthday;
	private Integer professionId;
	private String college;
	private String education;
	private String phone;
	private String sex;
	private String photo;
	private Date graduateDate;
}
