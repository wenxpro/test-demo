package com.example.testdemo.service;

import java.util.List;

public interface LoginService {

    String login(String loginName, String passWord);

    String getInfo(String loginName);

    String getMenu(List<String> permits);

}
