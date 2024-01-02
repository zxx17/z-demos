package com.util;

import org.springframework.stereotype.Service;


/**
 * @author Aimony
 * @date 2024/01/02 20:16
 * @describe
 */

@Service
public class IPService {

    /**
     * 获取ip信息
     *
     * @param ip ip
     * @return {@link IPInfo}
     */
    public IPInfo getIpInfo(String ip) {
        String ipAddress = IpUtils.getAddressByHttp(ip);  // 查询归属地
        // TODO (Aimony, 2024/1/2/002 22:11) 保存ip信息
        return IPInfo.builder().ip(ip).address(ipAddress).build();
    }
}
