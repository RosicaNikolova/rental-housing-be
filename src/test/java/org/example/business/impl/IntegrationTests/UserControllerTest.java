package org.example.business.impl.IntegrationTests;

import org.example.business.UserManager;
import org.example.domain.Role;
import org.example.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc()
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserManager userManager;


    //Testing that this service returns only the resources of the loggedin user
    @Test
    @WithMockUser(username = "rosica@mail.com", roles = {"HOMEOWNER"})
    void getUser_shouldReturn200WithUser_whenUserFound() throws Exception {
        User user = User.builder()
                .email("rosica@mail.com")
                .firstName("Rositsa")
                .lastName("Nikolova")
                .password("helo")
                .role(Role.HOMEOWNER)
                .build();
        when(userManager.getUser(10L)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/users/10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {"firstName":"Rositsa","lastName":"Nikolova","email":"rosica@mail.com"}
                        """));

        verify(userManager).getUser(10L);
    }

    //Testing what returns if the user is not present at all
    @Test
    @WithMockUser(username = "rositsa@gmail.com", roles = {"HOMEOWNER"})
    void getUser_shouldReturn404Error_whenStudentNotFound() throws Exception {
        when(userManager.getUser(10L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/users/10"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(userManager).getUser(10L);
    }

}