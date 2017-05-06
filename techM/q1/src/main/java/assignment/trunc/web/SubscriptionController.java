package assignment.trunc.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import assignment.trunc.service.OrderService;
import assignment.trunc.util.StringUtil;

/**
 * 
 * @author danuka
 * SubscriptionController serves subscription requests.
 *
 */
@Controller
public class SubscriptionController {
	
	@Resource(name = "OrderService")
	private OrderService orderService;

	@Value(value = "${subscription.order.truncate.limit}")
	private Integer truncateLimit;
	
	private static final Logger LOGGER = Logger.getLogger(SubscriptionController.class);
	
	/**
	 * End point which gives order details for subscriptions.
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/subscribe", method = RequestMethod.GET)
	public String subscribe (HttpServletRequest request) {
		String orderDetails = orderService.getOrderDetails(request);
		String truncatedOrderDetails = StringUtil.truncate(orderDetails, truncateLimit);
		LOGGER.info(truncatedOrderDetails);
		return orderDetails;		
	}

}
