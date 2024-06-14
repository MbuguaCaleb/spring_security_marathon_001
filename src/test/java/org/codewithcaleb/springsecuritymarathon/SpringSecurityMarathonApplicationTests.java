package org.codewithcaleb.springsecuritymarathon;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringSecurityMarathonApplicationTests {

   //we use mockMvc to perform integration tests to controllers(Because we assert statuses)
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("""
            When testing /hello endpoint authenticated,we should get back hello in the response body,and http status
            of 200_OK
            """
            )
    @WithMockUser //creates a mock security context,
    void helloAuthenticatedTest() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
        ;
    }

    @Test
    @DisplayName("""
            When testing /hello endpoint un-Authenticated,we should not get back hello in the response body,and http status
            of 401 Unauthorized
            """
    )
    void helloUnAuthenticatedTest() throws Exception {
        mockMvc. perform(get("/hello"))
                .andExpect(status().isUnauthorized());
    }
}
