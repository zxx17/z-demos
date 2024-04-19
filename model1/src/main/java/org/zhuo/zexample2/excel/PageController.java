package org.zhuo.zexample2.excel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author Xinxuan Zhuo
 * @version 2024/4/18
 * <p>
 *
 * </p>
 */
@Controller
public class PageController {

    @GetMapping("/exceloper")
    public String hello() {
        return "exceloper";
    }
}
