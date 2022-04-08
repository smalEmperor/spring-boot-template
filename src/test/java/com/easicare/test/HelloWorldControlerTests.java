package com.easicare.test;

import com.template.controller.AdviceController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

//@SpringBootTest
//class HelloWorldControlerTests {
//
//    private MockMvc mvc;
//
//    @BeforeEach
//    public void setUp() throws Exception {
//        mvc = MockMvcBuilders.standaloneSetup(new AdviceController()).build();
//    }
//
//    @Test
//    void getHello() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//    }
//}
