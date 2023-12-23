package org.zhuo.zother.qrcode.domain;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zhuo.zother.qrcode.entity.QRCodeReq;
import javax.annotation.Resource;


/**
 * @author Xinxuan Zhuo
 * @version 2023/12/16
 * <p>
 * 生成二维码controller
 * </p>
 */

@Slf4j
@CrossOrigin
@RestController
public class QRCodeController {


    @Resource
    private QRCodeService qrCodeService;

    /**
     * 生成二维码图片
     */
    @PostMapping("/qrcode")
    public ResponseEntity<?> generateQRCode(@RequestBody QRCodeReq req) {
        if(log.isInfoEnabled()){
            log.info("QRCodeController.generateQRCode.req: {}",req.toString());
        }
        try {
            return qrCodeService.generateQRCode(req);
        }catch (Exception e){
            log.error("QRCodeController.generateQRCode.error: {}",e.getMessage());
            return new ResponseEntity<>("生成失败",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
