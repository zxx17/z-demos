package org.zhuo.zother.qrcode.entity;

import lombok.Getter;

/**
 * 二维码模板类
 */
@Getter
public enum TemplateTypeEnum {

    TYPE1(1,"测试类型1");


    private int typeCode;
    private String desc;


    TemplateTypeEnum(Integer typeCode, String desc){
        this.typeCode = typeCode;
        this.desc = desc;
    }

    public static TemplateTypeEnum getByCode(int code) {
        for (TemplateTypeEnum templateEnumType : TemplateTypeEnum.values()) {
            if (templateEnumType.typeCode == code) {
                return templateEnumType;
            }
        }
        return null;
    }
}
