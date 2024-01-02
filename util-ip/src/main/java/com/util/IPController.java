package com.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aimony
 * @date 2024/01/02 20:19
 * @describe 查询ip信息
 */


@RestController
public class IPController {

    @Autowired
    private IPService ipService;

    @GetMapping("/ipInfo")
    public IPInfo getIpInfo(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty()) {
            ip = request.getRemoteAddr();
        }
        return ipService.getIpInfo(ip);
    }
}
