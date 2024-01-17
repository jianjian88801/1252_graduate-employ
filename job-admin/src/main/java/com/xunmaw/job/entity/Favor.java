package com.xunmaw.job.entity;


import lombok.Data;
import com.xunmaw.job.utils.Entity;
import java.util.Date;

@Data
public class Favor extends Entity {

	private Integer id;
	private Integer companyId;
	private Integer postId;
	private Integer studentId;
	private Date favorDate;

	private Post post;
	private Company company;
	private Student student;
}
