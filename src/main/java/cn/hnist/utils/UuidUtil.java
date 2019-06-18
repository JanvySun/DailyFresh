package cn.hnist.utils;

import java.util.UUID;

/**
 * 产生UUID随机字符串工具类
 */
public final class UuidUtil {

    /**
     * 产生一个UUID
     *
     * @return : 返回UUID
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
