package com.xunmaw.job.mapper;

import com.xunmaw.job.entity.Channel;

import java.util.List;

public interface ChannelMapper {

	int create(Channel channel);

	int delete(Integer id);

	int update(Channel channel);

	List<Channel> query(Channel channel);

	Channel detail(Integer id);

	int count(Channel channel);
}
