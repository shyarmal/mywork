package assignment.trunc.service;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface for order servie operations
 * @author danuka
 *
 */
public interface OrderService {
	
	/**
	 * Gets order details taking a request
	 * @param request
	 * @return order details
	 */
	public String getOrderDetails(HttpServletRequest request);

}
