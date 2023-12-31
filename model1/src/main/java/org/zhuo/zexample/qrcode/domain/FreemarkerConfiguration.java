package org.zhuo.zexample.qrcode.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  Freemarker配置类
 */
@Configuration
public class FreemarkerConfiguration {

    @Bean
    public freemarker.template.Configuration configuration() {
        freemarker.template.Configuration config = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_30);
        config.setClassForTemplateLoading(getClass(), "/templates/");
        return config;
    }

}