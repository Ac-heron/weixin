package com.herohuang.weixin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *
 *
 * @author Acheron
 */
@Controller
@RequestMapping("/fore")
public class WebIndexController {

    @RequestMapping("/{page:[A-Za-z0-9]+}")
    public String jump(@PathVariable("page") String page) {
        return  page;
    }

}
