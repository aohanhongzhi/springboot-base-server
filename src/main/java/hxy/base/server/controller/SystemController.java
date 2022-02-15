package hxy.base.server.controller;

import hxy.base.server.entity.BaseResponse;
import hxy.base.server.entity.exception.BaseException;
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
        return BaseResponse.success("SUCCESS", "SpringBoot基础骨架工程");
    }

    @RequestMapping(path = {"/exception"})
    public BaseResponse exception() {
        throw new BaseException("异常测试");
    }
}
