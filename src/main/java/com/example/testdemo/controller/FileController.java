package com.example.testdemo.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@Slf4j
public class FileController {


    @PostMapping("/api/upload")
    public String upload(@RequestParam("files") MultipartFile files[]) throws IOException {
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getOriginalFilename();
            File dest = new File("E:\\test\\"  + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            files[i].transferTo(dest);
        }
        return "success";
    }

    @GetMapping("/api/download")
    public void download(HttpServletResponse response) throws IOException {
        File file = new File("E:\\test\\download.txt");
        response.reset();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=download.txt");

        // 从文件读到servlet response输出流中
        try (FileInputStream inputStream = new FileInputStream(file)) {
            byte[] b = new byte[1024];
            int len;
            while ((len = inputStream.read(b)) > 0) {
                response.getOutputStream().write(b, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
