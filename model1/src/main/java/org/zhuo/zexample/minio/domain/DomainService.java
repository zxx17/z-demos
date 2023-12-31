package org.zhuo.zexample.minio.domain;

import io.minio.MinioClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author Xinxuan Zhuo
 * @version 2023/12/31
 * <p>
 *  MinIOService
 * </p>
 */
@Service("minio-domain-service")
public class DomainService {

    @Resource(name = "minio-client")
    private MinioClient minioClient;



}
