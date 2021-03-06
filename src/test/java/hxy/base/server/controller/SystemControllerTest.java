package hxy.base.server.controller;

import hxy.base.server.ServerApplication;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author eric
 * @program data
 * @description 接口测试
 * @date 2021/10/18
 */
@AutoConfigureMockMvc
@SpringBootTest(classes = ServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SystemControllerTest {

    private static final Logger log = LoggerFactory.getLogger("test");

    private static final String PREFIX2 = "/system/info";

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void test() throws Exception {
        RequestBuilder requestBuilder;
        requestBuilder = get("/system/info");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        response.setCharacterEncoding("UTF-8");
        String contentAsString = response.getContentAsString();
        // 还可以吧json进一步解析成对象，然后再去添加断言，判断比较是否满足要求。
        log.info(contentAsString);
    }

    @Test
    public void test2() throws Exception {
        RequestBuilder requestBuilder;
        mockMvc.perform(get(PREFIX2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("H0000"))
                .andExpect(jsonPath("$.message").value("查询成功"))
                .andExpect(jsonPath("$.data").exists())
                .andDo(MockMvcResultHandlers.print());
    }



}
