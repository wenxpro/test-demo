package com.example.testdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.Assert;

import java.io.File;
import java.io.FileInputStream;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class FileTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void uploadTest() throws Exception {
        File file = new File("C:\\Users\\Administrator\\Desktop\\上传.txt");
        MockMultipartFile firstFile = new MockMultipartFile("files", "上传.txt",
                MediaType.TEXT_PLAIN_VALUE, new FileInputStream(file));
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.multipart("/api/upload")
                        .file(firstFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
        String res = result.getResponse().getContentAsString();
        Assertions.assertEquals("success",res);
    }

    @Test
    void downloadTest() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/download")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
        String res = result.getResponse().getContentAsString();
        Assert.notNull(res,"下载信息不能为空！");
    }
}
