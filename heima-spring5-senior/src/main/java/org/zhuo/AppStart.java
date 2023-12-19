package org.zhuo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/15
 * <p>
 * 启动类
 * </p>
 */

@MapperScan("org.zhuo.**.mapper")
@SpringBootApplication
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class, args);
    }

}
