package br.com.zup.proposta.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class MockMvcRequest {

    public static MvcResult performPost(MockMvc mockMvc, String url, int status,
                                        ObjectMapper objectMapper, Object request) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().is(status)).andReturn();
    }

    public static MvcResult performGet(MockMvc mockMvc, String url, int status,
                                  ObjectMapper objectMapper, Object request) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().is(status)).andReturn();

    }
}
