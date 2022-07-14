package ru.liga.pictureserv.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@AutoConfigureMockMvc
public class ControllerWebTest {
    public static String createPersonUrl;
    public static String updatePersonUrl;
    public static HttpHeaders headers;
    public static RestTemplate restTemplate;
    public static JSONObject personJsonObject;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeClass
    public static void runBeforeAllTestMethods() {
        createPersonUrl = "http://localhost:8080/pict";
//        updatePersonUrl = "http://localhost:8082/spring-rest/updatePerson";

        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        personJsonObject = new JSONObject();
        personJsonObject.put("id", 1);
        personJsonObject.put("text", "John is Сударь");
    }

    @Test
    public void givenDataIsJson_whenDataIsPostedByPostForObject_thenResponseBodyIsNotNull()
            throws IOException {
        HttpEntity<String> request =
                new HttpEntity<String>(personJsonObject.toString(), headers);

        String personResultAsJsonStr =
                restTemplate.postForObject(createPersonUrl, request, String.class);
        JsonNode root = objectMapper.readTree(personResultAsJsonStr);

        assertNotNull(personResultAsJsonStr);
        assertNotNull(root);
        assertNotNull(root.path("name").asText());
    }
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void noParamGreetingShouldReturnDefaultMessage() throws Exception {
//
//        this.mockMvc.perform(get("/pict")).andDo(print()).andExpect(status().isOk())
//                .andExpect(jsonPath("$.content").value("Hello World"));
//    }
//
//    @Test
//    public void paramGreetingShouldReturnTailoredMessage() throws Exception {
//
//        this.mockMvc.perform(get("/todo").param("name", "Spring Community"))
//                .andDo(print()).andExpect(status().isOk())
//                .andExpect(jsonPath("$.content").value("Hello, Spring Community!"));
//    }

}