package com.example.testdemo;

import com.alibaba.fastjson2.JSON;
import com.example.testdemo.service.LoginService;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

@SpringBootTest
class LoginServiceTest {

    @Autowired
    private LoginService userService;

    /**
     * 登录测试
     */
    @Test
    void loginTest(){
        String res = userService.login("admin","admin");
        JSONObject jObj = JSON.parseObject(res,JSONObject.class);
        Integer num = jObj.getAsNumber("code").intValue();
        Assertions.assertEquals(0,num);
    }

    /**
     * 获取登录信息测试
     */
    @Test
    void getInfoTest(){
        String infoRes = userService.getInfo("admin");
        Assert.notNull(infoRes,"getInfo结果不能为空");

        Map<String,Object> map = JSON.parseObject(infoRes,Map.class);
        Assert.notEmpty((Collection<?>) map.get("permit"),"权限列表不能为空");
        Assert.notEmpty((Collection<?>) map.get("info"),"信息列表不能为空");
    }


    /**
     * 获取菜单信息测试
     */
    @Test
    void getMenuTest(){
        String infoRes = userService.getMenu(Arrays.asList("a","b","c"));
        Map<String,Object> map = JSON.parseObject(infoRes,Map.class);
        Assert.notNull(infoRes,"getMenu结果不能为空");
        Assert.notEmpty((Collection<?>) map.get("menus"),"菜单列表不能为空");
    }

}
