package hxy.base.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eric
 * @program server
 * @description 系统信息
 * @date 2021/11/2
 */
@RestController
public class SystemController {
    /**
     * @return
     */
    @RequestMapping("/system/info")
    public String index() {
        return "SpringBoot基础骨架工程";
    }
}
