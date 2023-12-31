package org.zhuo;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.zhuo.zexample.zconfig.EnvVar;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/15
 * <p>
 * 启动类
 * </p>
 */

@Slf4j
@MapperScan("org.zhuo.**.mapper")
@SpringBootApplication
public class AppStart {

    public static void main(String[] args) {
        SpringApplication.run(AppStart.class, args);

    }

    /**
     * 根据代码运行的环境设置一些参数
     * 比如说存储文件的路径
     */
    @Bean
    public void setOSEnv(){
        if (log.isInfoEnabled()){
            log.info("=====setOSEnvsetOSEnvsetOSEnv=====");
        }
        try{
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                EnvVar.setOsLegalPath("E:/z-spring-file");
            }else {
                EnvVar.setOsLegalPath("/home/z-spring-file");
            }
        }catch (Exception e){
            log.error("setOSEnv error {}",e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

}
