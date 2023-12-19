package org.zhuo.zother.qrcode.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/17
 * <p>
 * 二维码内容
 * 前端请求实体类
 * </p>
 */
@Data
public class QRCodeReq implements Serializable {

    private Integer templateTYpeCode;

    private String context;
}
