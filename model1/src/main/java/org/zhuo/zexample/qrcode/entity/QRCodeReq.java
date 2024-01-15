package org.zhuo.zexample.qrcode.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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

    private Integer templateTypeCode;

    private String context;

}
