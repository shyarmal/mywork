package assignment.trunc.web;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ReflectionUtils;

import assignment.trunc.config.WebConfig;
import assignment.trunc.service.OrderService;
import assignment.trunc.util.StringUtil;

/**
 * 
 * @author danuka
 * Test case for SubscriptionController
 *
 */
@WebAppConfiguration
@RunWith(PowerMockRunner.class)
@PrepareForTest(StringUtil.class)
@ContextConfiguration(classes = {WebConfig.class})
public class SubscriptionControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private OrderService orderService;
	
	@InjectMocks
	private SubscriptionController subscriptionController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		PowerMockito.mockStatic(StringUtil.class);
		mockMvc = MockMvcBuilders
				.standaloneSetup(subscriptionController).build();
		Field limitField = ReflectionUtils.findField(SubscriptionController.class, "truncateLimit");
		limitField.setAccessible(true);
		ReflectionUtils.setField(limitField, subscriptionController, new Integer(10));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSubscribe() {
        when(orderService.getOrderDetails(any(HttpServletRequest.class))).thenReturn("abc123");
        PowerMockito.when(StringUtil.truncate(any(String.class), any(Integer.class))).thenReturn("abc123");
        try {
			mockMvc.perform(get("/subscribe"))
			        .andExpect(status().isOk())
			        .andReturn().equals("abc123");
		} catch (Exception e) {
			e.printStackTrace();
			fail("subscribe end point failed ... ");
		}
        verify(orderService, times(1)).getOrderDetails(any(HttpServletRequest.class));
        verifyNoMoreInteractions(orderService);
	}

}
