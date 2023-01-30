package com.example.testdemo;

import com.alibaba.fastjson2.JSON;
import com.example.testdemo.domain.dto.UserDto;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.util.Assert;


/**
 * @see org.springframework.test.web.servlet.ResultActions 对请求结果操作
 */
@AutoConfigureMockMvc
@SpringBootTest
class UserMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void loginApiTest() throws Exception {
        //表单接口 测试
       MvcResult result = mockMvc
               .perform(MockMvcRequestBuilders.get("/api/login")
                       //表单 类型
                       .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                       //参数
                       .param("userName","admin")
                       .param("passWord","admin"))
               //打印响应信息
               .andDo(MockMvcResultHandlers.print())
               .andReturn();
        String res = result.getResponse().getContentAsString();
        Assert.notNull(res,"登录结果不能为空！");
        //验证 结果
        JSONObject jObj = JSON.parseObject(res,JSONObject.class);
        Integer num = jObj.getAsNumber("code").intValue();
        Assertions.assertEquals(0,num);
    }


    @Test
    void insertTest() throws Exception{
        //测试数据
        UserDto dto = new UserDto();
        dto.setAge(12);
        dto.setName("张三");
        //json 接口测试
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/api/insert")
                        //json 类型
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
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
