package org.zhuo.zother.zzzzconfig;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/20
 * <p>
 * 保存当前运行的环境
 * </p>
 */

public class EnvVar {

    private  static String osLegalPath;


    public static String getOsLegalPath() {
        return osLegalPath;
    }

    public static void setOsLegalPath(String var) {
        osLegalPath = var;
    }
}
