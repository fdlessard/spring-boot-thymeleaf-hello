package io.fdlessard.codebites.thymeleaf.hello;


import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.ModelAndView;

@WebMvcTest(controllers = WelcomeController.class)
public class WelcomeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  List<String> expectedList = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

  @Test
  public void main() throws Exception {
    ResultActions resultActions = mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(view().name("welcome"))
        .andExpect(model().attribute("message", equalTo("Bonjour")))
        .andExpect(model().attribute("tasks", is(expectedList)))
        .andExpect(content().string(containsString("Hello, Bonjour")));

    MvcResult mvcResult = resultActions.andReturn();
    ModelAndView mv = mvcResult.getModelAndView();
    //
  }

  // Get request with Param
  @Test
  public void hello() throws Exception {
    mockMvc.perform(get("/hello").param("name", "I Love Java!"))
        .andExpect(status().isOk())
        .andExpect(view().name("welcome"))
        .andExpect(model().attribute("message", equalTo("I Love Java!")))
        .andExpect(content().string(containsString("Hello, I Love Java!")));
  }


}
