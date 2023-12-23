package org.zhuo.zother.onlineshow.aliyunimm.pre;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/19
 * <p>
 * 获取密钥文件
 * </p>
 */

@Getter
@Component
public class AliYunAssessKey {

    /**
     * ACCESSKEYID自行添加
     */
    @Value("${access.key.id}")
    private String ACCESSKEYID;

    /**
     * ACCESSKEYSECRET自行添加
     */
    @Value("${access.key.secret}")
    private String ACCESSKEYSECRET;

    /**
     * oss地域
     */
    public static final String ALIYUN_OSS_ENDPOINT = "oss-cn-hangzhou.aliyuncs.com";

    /**
     * oss bucket名称
     */
    public static final String ALIYUN_OSS_BUCKETNAME = "zxx-test-oss-bucket";

    /**
     * 与oss绑定的IMM项目名称
     */
    public static final String ALIYUN_IMM_PJNAME = "demo20231220";
}
