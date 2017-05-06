package assignment.trunc.repo;

/**
 * 
 * @author danuka
 * Repository interface for order details.
 *
 */
public interface OrderDetailsRepo {
	
	/**
	 * Given a subscriber id, returns order details
	 * @param subId subscriber id
	 * @return order details
	 */
	public String findOrderDetails (String subId);

}
