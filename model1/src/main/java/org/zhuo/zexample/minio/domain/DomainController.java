package org.zhuo.zexample.minio.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zhuo.zexample.minio.entity.MinIOReq;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/31
 * <p>
 *  MinIOController
 * </p>
 */

@Slf4j
@RestController("minio-domain-controller")
@RequestMapping("/minio")
public class DomainController {

    /**
     * 创建存储bucket
     */
    @PostMapping("/create-bucket")
    public ResponseEntity<?> createBucket(@RequestBody MinIOReq req){

    }


    /**
     * 删除存储bucket
     */
    @PostMapping("/delete-bucket")
    public ResponseEntity<?> deleteBucket(@RequestBody MinIOReq req){


    }

    /**
     * 获取全部bucket
     */
    @GetMapping("/get-all-bucket")
    public ResponseEntity<?> getAllBucket(@RequestBody MinIOReq req){

    }


    /**
     * 文件上传
     */
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestBody MinIOReq req){

    }


    /**
     * 预览图片
     */
    @PostMapping("/preview-photo")
    public ResponseEntity<?> previewPhoto(@RequestBody MinIOReq req){

    }


    /**
     * 文件下载
     */
    @PostMapping("/download")
    public ResponseEntity<?> download(@RequestBody MinIOReq req){

    }

    /**
     * 查看文件对象
     */
    @PostMapping("/view-file-object")
    public ResponseEntity<?> viewFileObj(@RequestBody MinIOReq req){

    }


    /**
     * 删除文件对象
     */
    @PostMapping("/del-file-object")
    public ResponseEntity<?> delFileObj(@RequestBody MinIOReq req){

    }


}
