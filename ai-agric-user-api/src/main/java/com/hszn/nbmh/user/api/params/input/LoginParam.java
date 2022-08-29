package com.hszn.nbmh.user.api.params.input;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginParam implements Serializable {

    private String phone;

    private Integer type;
}