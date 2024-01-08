package org.zhuo.zexample.onlineshow.aliyunimm.domain;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zhuo.zexample.onlineshow.aliyunimm.entity.TbOssUpload;

import javax.annotation.Resource;
import java.util.List;

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
public class AliyunImmController {

    @Resource
    private AliyunImmService domainService;

    /**
     * 文件上传
     *
     * @param file     文件
     * @param fileType 类型码
     * @return booleanMessage
     */
    @PostMapping("/upload")
    public ResponseEntity<?> uploadOss(@RequestPart("file") MultipartFile file,
                                       @RequestParam Integer fileType) {
        if (log.isInfoEnabled()) {
            log.info("=====AliyunImmController.uploadOss.start=====");
        }
        try {
            domainService.uploadOss(file, fileType);
            return ResponseEntity.ok("上传成功");
        } catch (Exception e) {
            log.error("DomainController.uploadOss.error: {}", e.getMessage());
            return new ResponseEntity<>("上传失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 获取oss文件列表
     * 就是查数据库，简易实现
     */
    @GetMapping("/all")
    public ResponseEntity<?> getOss() {
        if (log.isInfoEnabled()) {
            log.info("=====AliyunImmController.getOss.start=====");
        }
        try {
            List<TbOssUpload> data = domainService.getOss();
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            log.error("DomainController.getOss.error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 文件预览
     *
     * 扩展：对文件的权限操作可以通过RBAC模型实现？
     * 根据调用的角色来设置参数....
     * 这里可能可以抽象出一个工厂的，先不写了有可能想的不对hahaha 23.12.20
     */
    @GetMapping
    public ResponseEntity<?> immServer(@RequestParam Integer fileId){
        if (log.isInfoEnabled()) {
            log.info("=====AliyunImmController.immServer.start=====");
        }
        try {
            JSONObject data = domainService.immServer(fileId);
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            log.error("DomainController.immServer.error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
