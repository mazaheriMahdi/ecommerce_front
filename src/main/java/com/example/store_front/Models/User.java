package com.example.store_front.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
@AllArgsConstructor
public class User implements Serializable {
    private String name;
    private String email;
    private double credit;
    private String avatarUrl;
    private Boolean isStaff;
}
