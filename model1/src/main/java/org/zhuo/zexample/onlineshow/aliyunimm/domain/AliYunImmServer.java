package org.zhuo.zexample.onlineshow.aliyunimm.domain;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zhuo.zexample.onlineshow.aliyunimm.pre.AliYunAssessKey;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/20
 * <p>
 *
 * </p>
 */

@Slf4j
@Component
public class AliYunImmServer {

    /**
     * container
     */
    private final Map<String, Object> resultMap = new HashMap<>();

    /**
     * 值注入
     * @param aliYunAssessKey 密钥文件
     */
    @Autowired
    private void autowire(AliYunAssessKey aliYunAssessKey) {
        if(log.isInfoEnabled()){
            log.info("package org.zhuo.zexample.onlineshow.aliyunimm.domain.AliYunImmServer执行依赖注入: {}", aliYunAssessKey);
        }
        resultMap.put("aliYunAssessKey",aliYunAssessKey);
    }

    /**
     * 获取ImmClient实例
     */
    public IAcsClient createClient() {
        log.info("=====获取ImmClient实例=====");
        AliYunAssessKey aliYunAssessKey = (AliYunAssessKey) resultMap.get("aliYunAssessKey");
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                aliYunAssessKey.getACCESSKEYID(),
                aliYunAssessKey.getACCESSKEYSECRET());
        return new DefaultAcsClient(profile);
    }
}
