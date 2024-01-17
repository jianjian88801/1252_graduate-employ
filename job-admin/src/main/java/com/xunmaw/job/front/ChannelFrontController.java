package com.xunmaw.job.front;

import com.xunmaw.job.entity.Channel;
import com.xunmaw.job.service.ChannelService;
import com.xunmaw.job.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/f/channel")
public class ChannelFrontController {

    @Autowired
    private ChannelService channelService;

    @PostMapping("/detail")
    public Result detail(Integer id) {
        return Result.success(channelService.detail(id));
    }

    @PostMapping("tree")
    public Result tree(@RequestBody Channel channel) {
        List<Channel> list = channelService.tree(channel);
        List<Map<String, Object>> mapList = new ArrayList<>();
        list.forEach(item -> {
            if (item.getParentId() == 0) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", item.getId());
                map.put("label", item.getName());
                if (isChildren(item.getId(), list)) {
                    map.put("children", children(item.getId(), list));
                }
                mapList.add(map);
            }
        });
        return Result.success(mapList);
    }

    //如何支持多级：递归
    public List<Map<String, Object>> children(int id, List<Channel> list) {
        List<Map<String, Object>> children = new ArrayList<>();
        for (Channel channel : list) {
            if (channel.getParentId() == id) {
                Map<String, Object> map = new HashMap();
                map.put("id", channel.getId());
                map.put("label", channel.getName());
                if (isChildren(channel.getId(), list)) {//出口
                    map.put("children", children(channel.getId(), list));
                }
                children.add(map);
            }
        }
        return children;
    }

    public boolean isChildren(int id, List<Channel> list) {
        boolean flag = false;
        for (Channel channel : list) {
            if (channel.getParentId() == id) {
                flag = true;
            }
        }
        return flag;
    }
}
