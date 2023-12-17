package org.zhuo.zother.qrcode.domain;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.zhuo.zother.qrcode.entity.QRCodeReq;
import org.zhuo.zother.qrcode.handler.TemplateFactory;
import org.zhuo.zother.qrcode.handler.TemplateHandler;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;



import static org.zhuo.zother.qrcode.entity.QRCodeEntity.HEIGHT;
import static org.zhuo.zother.qrcode.entity.QRCodeEntity.WIDTH;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/17
 * <p>
 *
 * </p>
 */

@Slf4j
@Service
public class QRCodeService {

    @Resource
    private TemplateFactory factory;
    private String MakeHtmlDomain;

    @Resource
    private void getMakeHtmlDomain(@Value("${make-html-domain}")String MakeHtmlDomain){
        this.MakeHtmlDomain = MakeHtmlDomain;
    }

    /**
     * 生成二维码
     */
    public ResponseEntity<?> generateQRCode(QRCodeReq req) {
        String htmlStr;
        try{
            TemplateHandler template = factory.getTemplateByCode(req.getTemplateTYpeCode());
            htmlStr = template.generateHtml(req.getContext());
        }catch (Exception e){
            log.error("QRCodeService.generateQRCode.error: {}",e.getMessage());
            throw new RuntimeException("生成html字符串失败");
        }
        try{
            // 生成二维码图片
            QRCodeWriter writer = new QRCodeWriter();
            String context = MakeHtmlDomain+htmlStr;
            BitMatrix matrix = writer.encode(context, BarcodeFormat.QR_CODE, WIDTH, HEIGHT);

            // 将二维码图片写入字节数组输出流中
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(matrix, "PNG", outputStream);

            // 创建字节数组资源对象
            byte[] bytes = outputStream.toByteArray();
            ByteArrayResource resource = new ByteArrayResource(bytes);

            // 构建响应头，指定Content-Disposition为attachment，文件名为qrcode.png
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=qrcode.png");
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(bytes.length)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        }catch (Exception e){
            log.error("QRCodeService.generateQRCode.error: {}",e.getMessage());
            throw new RuntimeException("生成二维码失败");
        }
    }

}
