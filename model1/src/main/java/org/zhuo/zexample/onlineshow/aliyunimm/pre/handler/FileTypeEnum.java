package org.zhuo.zexample.onlineshow.aliyunimm.pre.handler;

import lombok.Getter;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/19
 * <p>
 *  文件类型枚举
 * </p>
 */

@Getter
public enum FileTypeEnum {


    OFFICE_FILE(1, "OFFICE_FILE"),
    PHOTO_FILE(2, "PHOTO_FILE");

    private final int typeCode;
    private final String desc;


    FileTypeEnum(Integer typeCode, String desc) {
        this.typeCode = typeCode;
        this.desc = desc;
    }


    /**
     * 根据类型码找对应的文件枚举
     * @param typeCode 类型吗
     * @return 文件枚举类型
     */
    public static FileTypeEnum getByCode(int typeCode){
        for(FileTypeEnum fileTypeEnum: FileTypeEnum.values()){
            if(fileTypeEnum.getTypeCode() == typeCode){
                return fileTypeEnum;
            }
        }
        return null;
    }
}
