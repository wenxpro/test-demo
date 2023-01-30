package com.example.testdemo.controller;

import com.example.testdemo.domain.dto.UserDto;
import com.example.testdemo.domain.po.User;
import com.example.testdemo.service.LoginService;
import com.example.testdemo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Resource
    private LoginService loginService;
    @Resource
    private UserService userService;

    @GetMapping("/api/login")
    public String login(
            @RequestParam(required = true,name = "userName")String userName,
            @RequestParam(required = true,name = "passWord")String passWord
    ){
        return loginService.login(userName,passWord);
    }

    @GetMapping("/api/getLoginInfo")
    public String getLoginInfo(
            @RequestParam(required = true,name = "userName")String userName
    ){
        return loginService.getInfo(userName);
    }

    @GetMapping("/api/get")
    public User get(){
        return userService.get();
    }

    @PostMapping("/api/insert")
    public String insert(@RequestBody UserDto dto){
        User user = new User();
        BeanUtils.copyProperties(dto,user);
        user.setDeleted(1);
        userService.insert(user);
        return "success";
    }


    @PutMapping("/api/update")
    public String update(@RequestBody UserDto dto){
        User user = new User();
        BeanUtils.copyProperties(dto,user);
        userService.update(user);
        return "success";
    }

    @DeleteMapping("/api/delete/{id}")
    public String delete(@PathVariable Long id){
        userService.delete(id);
        return "success";
    }

}
