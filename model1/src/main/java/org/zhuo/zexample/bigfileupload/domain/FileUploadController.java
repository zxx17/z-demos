//package org.zhuo.zexample.bigfileupload.domain;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.zhuo.zexample.bigfileupload.entity.FileMergeReq;
//import org.zhuo.zexample.bigfileupload.entity.FileUploadReq;
//
//import javax.annotation.Resource;
//
//
///**
// * @author Xinxuan Zhuo
// * @version 2023/12/15
// * <p>
// *
// * </p>
// */
//
//@Slf4j
//@CrossOrigin
//@RestController
//public class FileUploadController {
//
//
//    @Resource
//    private  FileUploadService fileUploadService;
//
//    @PostMapping("/file/upload")
//    public ResponseEntity<Object> fileUpload(FileUploadReq req){
//        if(log.isInfoEnabled()){
//            log.info("FileUploadController.fileUpload.req{}", req.toString());
//        }
//        return fileUploadService.fileUpload(req);
//    }
//
//    @PostMapping("/file/merge")
//    public ResponseEntity<Object> fileMerge(@RequestBody FileMergeReq req){
//        if(log.isInfoEnabled()){
//            log.info("FileUploadController.fileMerge.req{}", req.toString());
//        }
//        return fileUploadService.merge(req);
//    }
//}
