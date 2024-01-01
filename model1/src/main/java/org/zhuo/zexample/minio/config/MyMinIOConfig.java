package org.zhuo.zexample.minio.config;

import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/31
 * <p>
 * MinIO配置类
 * </p>
 */

@Slf4j
@Configuration
public class MyMinIOConfig {

    @Value("${minio.endpoint}")
    private String ENDPOINT;

    @Value("${minio.root-user}")
    private String ROOT_USER;

    @Value("${minio.root-pwd}")
    private String ROOT_PWD;

    @Bean("minio-client")
    public MinioClient getClient(){
        try {
            log.info("=====获取MinIO连接=====");
            return MinioClient.builder()
                    .endpoint(ENDPOINT)
                    .credentials(ROOT_USER, ROOT_PWD)
                    .build();
        }catch (Exception e){
            log.error("MyMinIOConfig.getClient.error {}", e.getMessage());
            throw new RuntimeException("获取MinIO连接失败");
        }
    }

}
