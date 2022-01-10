package hxy.base.server.controller;

import hxy.base.server.entity.BaseResponse;
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
    @RequestMapping(path = {"/",})
    public String index() {
        return "SpringBoot基础骨架工程";
    }

    @RequestMapping(path = {"/system/info"})
    public BaseResponse system() {
        return BaseResponse.success("查询成功", "SpringBoot基础骨架工程");
    }
}
