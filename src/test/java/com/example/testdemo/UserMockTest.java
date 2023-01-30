package com.example.testdemo;


import com.alibaba.fastjson2.JSON;
import com.example.testdemo.domain.dto.UserDto;
import com.example.testdemo.domain.po.User;
import com.example.testdemo.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.util.Assert;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserMockTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @Test
    void searchTest() throws Exception {
        //构造mock
        User user = new User();
        user.setId(1L);
        user.setDeleted(1);
        user.setAge(12);
        user.setName("李四");
        when(userService.get()).thenReturn(user);
        //接口测试
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/get"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String res = result.getResponse().getContentAsString();
        User resUser = JSON.parseObject(res,User.class);
        Assert.notNull(resUser,"返回用户信息不能为空！");
    }

    @Test
    void insertTest() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setDeleted(1);
        user.setAge(12);
        user.setName("李四");
        doNothing().when(userService).insert(user);

        UserDto dto = new UserDto();
        BeanUtils.copyProperties(user,dto);
        //接口测试
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/api/insert")
                        //json 类型
                        .contentType(MediaType. APPLICATION_JSON_VALUE)
                        //参数
                        .content(JSON.toJSONString(dto)))
                //打印响应信息
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String res = result.getResponse().getContentAsString();
        Assert.notNull(res,"添加结果不能为空！");
        //验证 结果
        Assertions.assertEquals("success",res);

    }



}
