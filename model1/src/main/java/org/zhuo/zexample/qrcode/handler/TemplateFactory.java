package org.zhuo.zexample.qrcode.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.zhuo.zexample.qrcode.entity.TemplateTypeEnum;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/17
 * <p>
 * 二维码模板工厂
 * </p>
 */

@Component
public class TemplateFactory implements InitializingBean {

    /**
     * 注入所有的模板
     */
    @Resource
    private List<TemplateHandler> templateHandlerList;

    /**
     * 容器：模板枚举-key  模板类-value
     */
    private final Map<TemplateTypeEnum, TemplateHandler> handlerMap = new HashMap<>();


    /**
     * 根据模板枚举类型获得模板
     * @param typeCode 类型
     * @return 模板
     */
    public TemplateHandler getTemplateByCode(Integer typeCode){
        TemplateTypeEnum template = TemplateTypeEnum.getByCode(typeCode);
        return handlerMap.get(template);
    }


    /**
     * 在所有bean注入之后初始化容器
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        templateHandlerList.forEach(
                t-> handlerMap.put(t.getType(),t)
        );
    }

}
