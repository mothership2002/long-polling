package hello.longpollling.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
@AutoConfigureMockMvc
class TempControllerV3Test {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TempControllerV3 tempControllerV3;

    @Test
    void test() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("message", "hello");

        MvcResult mvcResult = mockMvc.perform(get("/api/result")
                                                .queryParams(params))
                                    .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println(response.getContentAsString());

    }
}