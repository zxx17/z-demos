package org.zhuo.zother.qrcode.handler;

import freemarker.template.TemplateException;
import org.zhuo.zother.qrcode.entity.TemplateTypeEnum;

import java.io.IOException;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/17
 * <p>
 * 对应模板生成对应内容二维码
 * </p>
 */

public interface TemplateHandler {

    TemplateTypeEnum getType();

    String generateHtml(Object context) throws IOException, TemplateException;
}
