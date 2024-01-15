package org.zhuo.zexample.qrcode.handler;

import freemarker.template.TemplateException;
import org.zhuo.zexample.qrcode.entity.TemplateTypeEnum;

import java.io.IOException;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/17
 * <p>
 * 对应模板生成对应内容二维码
 * </p>
 */

public interface TemplateHandler {

    /**
     * @return TemplateTypeEnum
     */
    TemplateTypeEnum getType();

    /**
     * 生成HtmlStr
     * @param context 正文数据
     * @param photoUrl 图片路径
     * @return HtmlStr
     */
    String generateHtml(Object context, String photoUrl) throws IOException, TemplateException;
}
