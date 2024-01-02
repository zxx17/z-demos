package com.util;

import lombok.Builder;
import lombok.Data;

/**
 * @author Aimony
 * @date 2024/01/02 20:15
 * @describe
 */

@Data
@Builder
public class IPInfo {
    private String ip;
    private String address;
    // TODO (Aimony, 2024/1/2/002 22:14) 其他ip信息字段 ...
}

