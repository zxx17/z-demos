package org.zhuo.zother.qrcode.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.zhuo.zother.qrcode.entity.TemplateTypeEnum;

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

    @Resource
    private List<TemplateHandler> templateHandlerList;

    private final Map<TemplateTypeEnum, TemplateHandler> handlerMap = new HashMap<>();


    public TemplateHandler getTemplateByCode(Integer typeCode){
        TemplateTypeEnum template = TemplateTypeEnum.getByCode(typeCode);
        return handlerMap.get(template);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        templateHandlerList.forEach(
                t-> handlerMap.put(t.getType(),t)
        );
    }

}
