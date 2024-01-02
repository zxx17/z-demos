package com.util;

import cn.hutool.core.net.NetUtil;
import cn.hutool.http.HtmlUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import org.springframework.web.client.RestTemplate;

/**
 * @author Aimony
 * @date 2024/01/02 21:39
 * @describe ip查询工具
 */

@Data
public class IpUtils {

    /**
     * 太平洋网开放 API
     */
    private static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp?ip=%s&json=true";

    /**
     * 查询 IP 归属地
     *
     * @param ip IP 地址
     * @return IP 归属地
     */
    public static String getAddress(String ip) {
        return getAddressByHttp(ip);
        // TODO (Aimony, 2024/1/2/002 21:45) 本地库解析ip
    }

    /**
     * 查询 IP 归属地（网络解析）
     *
     * @param ip IP 地址
     * @return IP 归属地
     */
    public static String getAddressByHttp(String ip) {
        if (isInnerIp(ip)) {
            return "内网IP";
        }
        RestTemplate restTemplate = new RestTemplate();
        String api = String.format(IP_URL, ip);
        String jsonObj = restTemplate.getForObject(api, String.class);
        JSONObject ipObj = JSONUtil.parseObj(jsonObj);
        System.out.println(ipObj);
        return ipObj.getStr("addr");
    }


    /**
     * 是否为内网 IPv4
     *
     * @param ip IP 地址
     * @return 是否为内网 IP
     */
    public static boolean isInnerIp(String ip) {
        ip = "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : HtmlUtil.cleanHtmlTag(ip);
        return NetUtil.isInnerIP(ip);
    }
}
