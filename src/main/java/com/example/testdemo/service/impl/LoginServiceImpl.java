package com.example.testdemo.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.testdemo.service.LoginService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public String login(String loginName, String passWord){
        if("admin".equals(loginName) && "admin".equals(passWord)){
            return "{\"code\":\"0\",\"msg\":\"success\"}";
        }
        return "{\"code\":\"1\",\"msg\":\"fail\"}";
    }

    @Override
    public String getInfo(String loginName){
        if(!"admin".equals(loginName)){
            return null;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("permit", Arrays.asList("a","b","c"));
        map.put("info", Arrays.asList("b","a","c"));
        return JSON.toJSONString(map);
    }

    @Override
    public String getMenu(List<String> permits){
        if(CollectionUtils.isEmpty(permits)){
            return null;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("menus", Arrays.asList("a","b","c"));
        return JSON.toJSONString(map);
    }
}
