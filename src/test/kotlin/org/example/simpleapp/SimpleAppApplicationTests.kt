package org.example.simpleapp

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest
@AutoConfigureMockMvc
class SimpleAppApplicationTests {

    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    fun severalQueryParams() {
        mvc.perform(get("?year=2000&currentDate=12122000"))
                .andExpect(status().isBadRequest)
    }

    @Test
    fun noneQueryParams() {
        mvc.perform(get("/"))
                .andExpect(status().isBadRequest)
    }

    @Test
    fun year2017() {
        mvc.perform(get("?year=2017").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataMessage").value("13/09/17"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value("200"))
    }

    @Test
    fun year2020() {
        mvc.perform(get("?year=2020").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataMessage").value("12/09/20"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value("200"))
    }

    @Test
    fun year2000() {
        mvc.perform(get("?year=2000").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataMessage").value("12/09/00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value("200"))
    }

    @Test
    fun year2100() {
        mvc.perform(get("?year=2100").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataMessage").value("13/09/00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value("200"))
    }

    @Test
    fun currentDate10092017() {
        mvc.perform(get("?currentDate=10092017").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataMessage").value("3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value("200"))
    }

    @Test
    fun currentDate10092000() {
        mvc.perform(get("?currentDate=10092000").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataMessage").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value("200"))
    }

    @Test
    fun currentDate20092000() {
        mvc.perform(get("?currentDate=20092000").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataMessage").value("358"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value("200"))
    }
}