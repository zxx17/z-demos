package org.zhuo.zexample.minio.domain;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.Contents;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zhuo.zexample.minio.entity.MinIOReq;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author Xinxuan Zhuo
 * @version 2023/12/31
 * <p>
 * MinIOService
 * </p>
 */
@Slf4j
@Service
public class MinIOService {

    @Resource(name = "minio-client")
    private MinioClient minioClient;

    /**
     * 创建存储bucket
     */
    public ResponseEntity<?> createBucket(MinIOReq req) {
        try {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(req.getBucketName())
                    .build());
            return new ResponseEntity<>("创建成功", HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("minio-domain-service.createBucket.error {}", e.getMessage());
            return new ResponseEntity<>("创建失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 删除存储bucket
     */
    public ResponseEntity<?> deleteBucket(MinIOReq req) {
        try {
            minioClient.removeBucket(RemoveBucketArgs.builder()
                    .bucket(req.getBucketName())
                    .build());
            return new ResponseEntity<>("删除成功", HttpStatus.OK);
        } catch (Exception e) {
            log.error("minio-domain-service.deleteBucket.error {}", e.getMessage());
            return new ResponseEntity<>("删除失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取全部bucket
     */
    public ResponseEntity<?> getAllBucket() {
        try {
            List<Bucket> buckets = minioClient.listBuckets();
            return new ResponseEntity<>(buckets, HttpStatus.OK);
        } catch (Exception e) {
            log.error("minio-domain-service.getAllBucket.error {}", e.getMessage());
            return new ResponseEntity<>("查询失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 文件上传
     */
    public ResponseEntity<?> uploadFile(String bucketName, MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            if (StringUtils.isBlank(originalFilename)) {
                return new ResponseEntity<>("文件名不能为空", HttpStatus.BAD_REQUEST);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String objectName = sdf.format(new Date()) + "/" + originalFilename;

            PutObjectArgs objectArgs = PutObjectArgs.builder().bucket(bucketName).object(objectName)
                    .stream(file.getInputStream(), file.getSize(), -1).contentType(file.getContentType()).build();
            //文件名称相同会覆盖
            minioClient.putObject(objectArgs);
            return ResponseEntity.ok("上传成功");
        } catch (Exception e) {
            log.error("minio-domain-service.uploadFile.error {}", e.getMessage());
            return new ResponseEntity<>("上传失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 预览图片
     */
    public ResponseEntity<?> previewPhoto(MinIOReq req) {
        // 查看文件地址
        GetPresignedObjectUrlArgs build = GetPresignedObjectUrlArgs.builder()
                .bucket(req.getBucketName()).object(req.getObjectName()).method(Method.GET).build();
        try {
            String url = minioClient.getPresignedObjectUrl(build);
            return new ResponseEntity<>(url, HttpStatus.OK);
        } catch (Exception e) {
            log.error("minio-domain-service.previewPhoto.error {}", e.getMessage());
            return new ResponseEntity<>("获取预览地址失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 查看文件对象
     */
    public ResponseEntity<?> viewFileObj(MinIOReq req) {
        List<JSONObject> resultList = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        try {
            boolean recursive = true;
            Iterable<Result<Item>> results = minioClient.listObjects(
                    ListObjectsArgs.builder().bucket(req.getBucketName()).recursive(recursive).build());

            for (Result<Item> result : results) {
                Item item = result.get();
                items.add(item);

                if (item.isDir()) {
                    // 如果是文件夹，递归获取子文件夹和文件
                    List<Item> subItems = getSubItems(req, item.objectName());
                    items.addAll(subItems);
                }
            }
            for (Item item : items) {
                JSONObject tmp = new JSONObject();
                Contents content = (Contents) item;
                tmp.put("objectName", content.objectName());
                tmp.put("lastModified", content.lastModified().plusHours(8));
                resultList.add(tmp);
            }
            return new ResponseEntity<>(resultList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("minio-domain-service.viewFileObj.error {}", e.getMessage());
            return new ResponseEntity<>("查看文件对象失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private List<Item> getSubItems(MinIOReq req, String prefix) {
        List<Item> subItems = new ArrayList<>();
        try {
            boolean recursive = true;
            Iterable<Result<Item>> results = minioClient.listObjects(
                    ListObjectsArgs.builder().bucket(req.getBucketName()).prefix(prefix).recursive(recursive).build());

            for (Result<Item> result : results) {
                subItems.add(result.get());
            }
        } catch (Exception e) {
            log.error("minio-domain-service.viewFileObj->getSubItems.error---Error retrieving sub items: {}", e.getMessage());
        }
        return subItems;
    }


    /**
     * 删除文件对象
     */
    public ResponseEntity<?> delFileObj(MinIOReq req) {
        try {
            minioClient.removeObject( RemoveObjectArgs.builder().bucket(req.getBucketName()).object(req.getObjectName()).build());
            return new ResponseEntity<>("删除文件对象成功", HttpStatus.OK);
        }catch (Exception e){
            log.error("minio-domain-service.delFileObj.error {}", e.getMessage());
            return new ResponseEntity<>("删除文件对象失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
