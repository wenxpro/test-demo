package com.example.testdemo.domain.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String name;

    private Integer age;
    private Integer deleted;

}
