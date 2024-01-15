package org.zhuo.zexample.qrcode.handler;

import com.alibaba.fastjson.JSONObject;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.zhuo.zexample.qrcode.entity.TemplateTypeEnum;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/17
 * <p>
 * 测试类型1
 * </p>
 */

@Component
public class TestTemplate implements TemplateHandler{

    /**
     * FreeMarker模板引擎配置
     */
    @Resource
    private FreeMarkerConfigurer freemarkerConfig;

    /**
     * @return TemplateTypeEnum.TYPE1
     */
    @Override
    public TemplateTypeEnum getType() {
        return TemplateTypeEnum.TYPE1;
    }

    /**
     * TestTemplate htmlStr生成策略
     */
    @Override
    public String generateHtml(Object context, String photoUrl) throws IOException, TemplateException {
        // 构造数据模型
        Map<String, Object> model = new HashMap<>();
        JSONObject jsonContext = JSONObject.parseObject((String) context);
        model.put("productName", jsonContext.get("productName"));
        model.put("productBrand", jsonContext.get("productBrand"));
        model.put("productIngredients", jsonContext.get("productIngredients"));
        model.put("productSpecifications", jsonContext.get("productSpecifications"));
        model.put("productStorage", jsonContext.get("productStorage"));
        model.put("productPrice", jsonContext.get("productPrice"));
        model.put("productDetails", jsonContext.get("productDetails"));

        model.put("productPhoto", photoUrl);

        // 加载HTML模板
        Template template = freemarkerConfig.getConfiguration().getTemplate("template.html");

        // 渲染HTML字符串
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        template.process(model, writer);
        return outputStream.toString();
    }
}
