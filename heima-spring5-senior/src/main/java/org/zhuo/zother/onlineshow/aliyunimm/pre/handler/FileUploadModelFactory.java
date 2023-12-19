package org.zhuo.zother.onlineshow.aliyunimm.pre.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.zhuo.zother.qrcode.entity.TemplateTypeEnum;
import org.zhuo.zother.qrcode.handler.TemplateHandler;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/19
 * <p>
 *
 * </p>
 */

@Component
public class FileUploadModelFactory implements InitializingBean {

    @Resource
    private List<FileTypeHandler> fileTypeHandlerList;

    private final Map<FileTypeEnum, FileTypeHandler> handlerMap = new HashMap<>();

    /**
     * 获取对应工厂实现
     */
    public FileTypeHandler getModelByCode(Integer typeCode){
        FileTypeEnum template = FileTypeEnum.getByCode(typeCode);
        return handlerMap.get(template);
    }

    /**
     * 在bean注入完成后填充容器
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        fileTypeHandlerList.forEach(
                t -> handlerMap.put(t.getFileType(), t)
        );
    }
}
