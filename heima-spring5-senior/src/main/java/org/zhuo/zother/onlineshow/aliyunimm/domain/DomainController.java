package org.zhuo.zother.onlineshow.aliyunimm.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.zhuo.zother.onlineshow.aliyunimm.pre.handler.FileUploadModelFactory;

import javax.annotation.Resource;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/19
 * <p>
 * immController
 * </p>
 */

@Slf4j
@RestController
@RequestMapping("/imm")
public class DomainController {

    @Resource
    private DomainService domainService;

    /**
     * 文件上传
     * @param file 文件
     * @param fileType 类型码
     * @return booleanMessage
     */
    @PostMapping("/upload")
    public ResponseEntity<?> uploadOss(@RequestParam("file") MultipartFile file,
                                       @RequestParam Integer fileType){
        try {
            if (log.isInfoEnabled()){
                log.info("=====DomainController.uploadOss.start=====");
            }
            domainService.uploadOss(file,fileType);
            return ResponseEntity.ok("上传成功");
        }catch (Exception e){
            log.error("DomainController.uploadOss.error: {}",e.getMessage());
            return new ResponseEntity<>("上传失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
