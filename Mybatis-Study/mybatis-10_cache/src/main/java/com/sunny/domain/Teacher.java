package com.sunny.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Teacher implements Serializable {
    private Long id;
    private String name;
}
