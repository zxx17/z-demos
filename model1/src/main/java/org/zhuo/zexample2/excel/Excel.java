package org.zhuo.zexample2.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface Excel {

    /**
     * 获取实体属性对应单元格位置
     */
    int index() default 0;


    /**
     * 导出的表格的工作簿的名字
     */
    String sheetName() default "sheet";

    /**
     * 设置表头名称
     */
    String columnName() default "暂无";

    /**
     * 设置列宽度
     */
    int width() default 4000;
}
