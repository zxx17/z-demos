package org.zhuo.zexample.qrcode.entity;

import lombok.Getter;

/**
 * 二维码模板枚举
 */
@Getter
public enum TemplateTypeEnum {

    TYPE1(1,"测试类型1");


    private final int typeCode;
    private final String desc;


    TemplateTypeEnum(Integer typeCode, String desc){
        this.typeCode = typeCode;
        this.desc = desc;
    }

    /**
     * 根据typeCode或者对应枚举
     * @param code typeCode
     */
    public static TemplateTypeEnum getByCode(int code) {
        for (TemplateTypeEnum templateEnumType : TemplateTypeEnum.values()) {
            if (templateEnumType.typeCode == code) {
                return templateEnumType;
            }
        }
        return null;
    }
}
