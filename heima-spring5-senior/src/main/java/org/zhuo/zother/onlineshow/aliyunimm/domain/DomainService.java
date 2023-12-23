package org.zhuo.zother.onlineshow.aliyunimm.domain;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.imm.model.v20200930.GenerateWebofficeTokenRequest;
import com.aliyuncs.imm.model.v20200930.GenerateWebofficeTokenResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import org.zhuo.zother.onlineshow.aliyunimm.async.SaveFileEvent;
import org.zhuo.zother.onlineshow.aliyunimm.entity.TbOssUpload;
import org.zhuo.zother.onlineshow.aliyunimm.pre.handler.FileTypeHandler;
import org.zhuo.zother.onlineshow.aliyunimm.pre.handler.FileUploadModelFactory;
import org.zhuo.zother.zzzzutils.FileNameUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.zhuo.zother.onlineshow.aliyunimm.pre.AliYunAssessKey.ALIYUN_IMM_PJNAME;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/19
 * <p>
 * immServer service
 * </p>
 */

@Slf4j
@Service
public class DomainService {

    @Resource
    private FileUploadModelFactory factory;

    @Resource
    private TbOssUploadService uploadService;

    @Resource
    private ApplicationEventPublisher publishSaveEvent;

    @Resource
    private AliYunImmServer aliYunImmServer;

    /**
     * 文件上传
     *
     * @param file     文件
     * @param fileType 类型码
     */
    public void uploadOss(MultipartFile file, Integer fileType) {
        FileTypeHandler uploadModel = factory.getModelByCode(fileType);
        String httpOssUrl = uploadModel.upload(file);
        String ossUrl = FileNameUtil.ossHttpToOssProtocol(httpOssUrl);

        //数据存储
        TbOssUpload ossUpload = new TbOssUpload();
        ossUpload.setFileUrl(ossUrl);
        ossUpload.setCreatedTime(new Date());
        ossUpload.setIsDeleted(0);
        uploadService.insert(ossUpload);

        //文件存储 异步 MQ 通知
        publishSaveEvent.publishEvent(new SaveFileEvent(file));
    }

    /**
     * 获取oss文件列表
     * 就是查数据库，简易实现
     */
    public List<TbOssUpload> getOss() {
        return uploadService.queryAll();
    }

    /**
     * 文件预览
     */
    public JSONObject immServer(Integer fileId) throws ClientException {

        IAcsClient client = aliYunImmServer.createClient();
        GenerateWebofficeTokenRequest request = new GenerateWebofficeTokenRequest();

        request.setProjectName(ALIYUN_IMM_PJNAME);
        TbOssUpload ossUpload = uploadService.queryById(fileId);
        request.setSourceURI(ossUpload.getFileUrl());
        //request更多配置见阿里云imm文档
        GenerateWebofficeTokenResponse response = client.getAcsResponse(request);
        JSONObject resultJson = new JSONObject();
        resultJson.put("requestId",response.getRequestId());
        resultJson.put("webofficeURL",response.getWebofficeURL());
        resultJson.put("accessToken",response.getAccessToken());
        resultJson.put("refreshToken",response.getRefreshToken());
        resultJson.put("accessTokenExpiredTime",response.getAccessTokenExpiredTime());
        resultJson.put("refreshTokenExpiredTime",response.getRefreshTokenExpiredTime());
//        JSONObject resultJson = JSONObject.parseObject(response.toString());
        return resultJson;

    }


}
