package com.meli.mutants;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.mutants.application.rest.model.request.MutantAnalyzerRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = MutantServerApplication.class)
public class MutantServerApplicationTest {

    public static final String STATS_URL = "/stats";

    public static final String MUTANTS_URL = "/mutant";

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Get All Stats")
    @Test
    public void getAllStats() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get(STATS_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        assertNotNull(response);
    }

    @DisplayName("Is a mutant")
    @Test
    public void isMutant() throws Exception {
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};

        MutantAnalyzerRequest analyzerRequest = new MutantAnalyzerRequest();
        analyzerRequest.setDna(dna);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post(MUTANTS_URL)
                        .content(transformJson(analyzerRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        assertNotNull(response);
    }

    @DisplayName("Is not mutant")
    @Test
    public void isNotMutant() throws Exception {
        String[] dna = {"ATGGGA","CGGTGC","TTCTGT","AGTATG","CCGCTA","TCACTT"};

        MutantAnalyzerRequest analyzerRequest = new MutantAnalyzerRequest();
        analyzerRequest.setDna(dna);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post(MUTANTS_URL)
                        .content(transformJson(analyzerRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        assertNotNull(response);
    }

    private String transformJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

}
