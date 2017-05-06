package assignment.trunc.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import assignment.trunc.repo.OrderDetailsRepo;
import assignment.trunc.service.OrderService;

/**
 * 
 * @author danuka
 * Service class for orders
 *
 */
@Service(value = "OrderService")
public class OrderServiceImpl implements OrderService {

	@Value(value = "${subscription.id.request.param}")
	private String subscriptionIdParam; /** request parameter holding subscriber id */
	
	@Autowired
	private OrderDetailsRepo orderDetailsRepo;
	private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);

	/**
	 * Gets the order details for a subscriber taking a request
	 * @param request
	 * @return order details
	 */
	public String getOrderDetails(HttpServletRequest request) {
		String subId = request.getParameter(subscriptionIdParam);
		LOGGER.debug(String.format("Retrieving order details for subscription %s : %s", subscriptionIdParam, subId));
		return orderDetailsRepo.findOrderDetails(subId);
	}

}
