package danuka.web.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import danuka.config.WebConfig;
import danuka.rest.domain.CountResult;
import danuka.rest.domain.SearchTexts;
import danuka.rest.domain.WordCount;
import danuka.service.CounterService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Danuka
 * Test case for CounterController
 *
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
public class CounterControllerTest {

	private MockMvc mockMvc;

    @Mock
    private CounterService counterService;

    @InjectMocks
    private CounterController counterController;


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(counterController)
                .build();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for /counter-api/top/3 end point
	 * public ResponseEntity<String> topWordCount (@PathVariable Integer limit)
	 */
	@Test
	public void testTopWordCount() throws Exception {

        when(counterService.findTop(3)).thenReturn("Returning top 3");

        mockMvc.perform(get("/counter-api/top/3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/csv;charset=ISO-8859-1"))
                .andReturn().equals("Returning top 3");

        verify(counterService, times(1)).findTop(3);
        verifyNoMoreInteractions(counterService);

	}

	/**
	 * Test method for /counter-api/search end point
	 * public CountResult searchTextCounts(SearchTexts searchTexts)
	 */
	@Test
	public void testSearch() throws Exception {
        when(counterService.searchTextCounts(any(SearchTexts.class))).thenReturn(new CountResult());

        mockMvc.perform(
                post("/counter-api/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new SearchTexts())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

        mockMvc.perform(
                post("/counter-api/search")
                        .contentType(MediaType.ALL)
                        .content(asJsonString(new SearchTexts())))
                .andExpect(status().isUnsupportedMediaType());
        
        verify(counterService, times(1)).searchTextCounts(any(SearchTexts.class));
        verifyNoMoreInteractions(counterService);

	}
	
	public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}