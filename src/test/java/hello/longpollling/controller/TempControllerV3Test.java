package hello.longpollling.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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

        MvcResult mvcResult =
                mockMvc.perform(get("/api/result")
                                .queryParams(params))
                        .andReturn();

        this.mockMvc
                .perform(asyncDispatch(mvcResult))
                .andDo(print())
                .andExpect(status().isOk());
    }
}