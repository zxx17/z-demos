package org.zhuo.zexample.minio.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zhuo.zexample.minio.entity.MinIOReq;
import org.zhuo.zexample.onlineshow.aliyunimm.domain.DomainService;
import org.zhuo.zexample.zaoplog.anno.LogAnno;

import javax.annotation.Resource;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/31
 * <p>
 *  MinIOController
 * </p>
 */

@Slf4j
@RestController
@RequestMapping("/minio")
public class MinIOController {

    @Resource
    private MinIOService minIOService;

    /**
     * 创建存储bucket
     */
    @LogAnno
    @PostMapping("/create-bucket")
    public ResponseEntity<?> createBucket(@RequestBody MinIOReq req){
        return minIOService.createBucket(req);
    }


    /**
     * 删除存储bucket
     */
    @LogAnno
    @PostMapping("/delete-bucket")
    public ResponseEntity<?> deleteBucket(@RequestBody MinIOReq req){
        return minIOService.deleteBucket(req);
    }

    /**
     * 获取全部bucket
     */
    @LogAnno
    @GetMapping("/get-all-bucket")
    public ResponseEntity<?> getAllBucket(){
        return minIOService.getAllBucket();
    }


    /**
     * 文件上传
     */
    @LogAnno
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam String bucketName, @RequestParam("file") MultipartFile file){
        return minIOService.uploadFile(bucketName, file);
    }


    /**
     * 预览图片
     */
    @LogAnno
    @PostMapping("/preview-photo")
    public ResponseEntity<?> previewPhoto(@RequestBody MinIOReq req){
        return minIOService.previewPhoto(req);
    }


    /**
     * 文件下载
     */
    @PostMapping("/download")
    public ResponseEntity<?> download(@RequestBody MinIOReq req){
        return null;
    }

    /**
     * 查看文件对象
     */
    @LogAnno
    @PostMapping("/view-file-object")
    public ResponseEntity<?> viewFileObj(@RequestBody MinIOReq req){
        return minIOService.viewFileObj(req);
    }


    /**
     * 删除文件对象
     */
    @LogAnno
    @PostMapping("/del-file-object")
    public ResponseEntity<?> delFileObj(@RequestBody MinIOReq req){
        return minIOService.delFileObj(req);
    }


}
