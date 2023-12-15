package org.zhuo.zother.utils;

import java.io.File;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/15
 * <p>
 *
 * </p>
 */

public class FileNameUtil {

    /**
     * 获取文件的扩展名
     * @param file 文件对象
     * @return 文件扩展名
     */
    public static String extName(File file) {
        if (file.isFile()) {
            String name = file.getName();
            int index = name.lastIndexOf(".");
            if (index == -1) {
                return "";
            }
            return name.substring(index + 1);
        }
        return "";
    }

    /**
     * 获取文件的主文件名
     * @param file 文件对象
     * @return 文件主文件名
     */
    public static String mainName(File file) {
        if (file.isFile()) {
            String name = file.getName();
            int index = name.lastIndexOf(".");
            if (index == -1) {
                return name;
            }
            return name.substring(0, index);
        }
        return "";
    }
}
