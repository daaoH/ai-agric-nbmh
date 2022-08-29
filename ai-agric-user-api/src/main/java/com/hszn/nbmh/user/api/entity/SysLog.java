package com.hszn.nbmh.user.api.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 日志表
 * </p>
 *
 * @author yuan
 * @since 2022-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 日志类型
     */
    private String type;

    /**
     * 日志标题
     */
    private String title;

    /**
     * 服务ID
     */
    private String serviceId;

    /**
     * 操作IP地址
     */
    private String remoteAddr;

    /**
     * 用户代理
     */
    private String userAgent;

    /**
     * 请求URI
     */
    private String requestUri;

    /**
     * 操作方式
     */
    private String method;

    /**
     * 操作提交的数据
     */
    private String params;

    /**
     * 执行时间
     */
    private Long time;

    /**
     * 删除标记
     */
    private String status;

    /**
     * 异常信息
     */
    private String exception;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新人
     */
    private String updateBy;


}
