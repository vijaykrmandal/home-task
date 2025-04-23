package com.bottomline.exercise.autocomplete.controller;

import com.bottomline.exercise.autocomplete.service.AutoCompleteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class AutoCompleteControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@SuppressWarnings("removal")
	@MockBean
	private AutoCompleteService autoCompleteService;

	private List<String> dummySuggestions;

	@BeforeEach
	void setUp() {
		dummySuggestions = List.of("App", "Apple", "Application");
	}

	@Test
	void shouldReturnSuggestionsForValidPrefix() throws Exception {
		when(autoCompleteService.autocomplete("App")).thenReturn(dummySuggestions);

		mockMvc.perform(get("/api/autocomplete").param("prefix", "App")).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(3)).andExpect(jsonPath("$[0]").value("App"))
				.andExpect(jsonPath("$[1]").value("Apple")).andExpect(jsonPath("$[2]").value("Application"));
	}

	@Test
	void shouldReturnEmptyListWhenNoMatch() throws Exception {
		when(autoCompleteService.autocomplete("xyz")).thenReturn(Collections.emptyList());

		mockMvc.perform(get("/api/autocomplete").param("prefix", "xyz")).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(0));
	}
}