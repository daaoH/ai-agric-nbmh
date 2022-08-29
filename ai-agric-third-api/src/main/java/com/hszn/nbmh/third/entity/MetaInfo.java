package com.hszn.nbmh.third.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class MetaInfo implements Serializable {

    private String apdidToken;

    private String appName;

    private String appVersion;

    private String bioMetaInfo;

    private String deviceModel;

    private String deviceType;

    private String identityVer;

    private String osVersion;

    private String sdkVersion;

}
