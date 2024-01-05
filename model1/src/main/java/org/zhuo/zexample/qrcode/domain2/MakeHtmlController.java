package org.zhuo.zexample.qrcode.domain2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Xinxuan Zhuo
 * @version 2023/12/17
 * <p>
 *  解析二维码Controller
 * </p>
 */

@Controller
public class MakeHtmlController {

    /**
     * 简易 解析器
     */
    @GetMapping("/make-html/index")
    public String index(@RequestParam(required = false) String htmlStr, Model model){
        model.addAttribute("htmlStr", htmlStr);
        return "index";
    }

}
