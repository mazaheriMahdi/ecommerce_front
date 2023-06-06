package com.example.store_front.Models.RequestModel;

import com.google.gson.JsonElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
public class SignInRequestModel  {
    private  String name ;
    private String email;
    private  String password;
    private String avatar_url;
}
