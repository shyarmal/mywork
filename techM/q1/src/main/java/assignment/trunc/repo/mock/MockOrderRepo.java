package assignment.trunc.repo.mock;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import assignment.trunc.repo.OrderDetailsRepo;

/**
 * 
 * @author danuka
 * Mock repository implementation of OrderDetailsRepo
 *
 */
@Repository
public class MockOrderRepo implements OrderDetailsRepo {

	/**
	 * Mock implementation to get order details.
	 * @param subId subscriber id
	 * @return a random String 
	 */
	public String findOrderDetails(String subId) {
		return (subId != null) ? 
				UUID.nameUUIDFromBytes(subId.getBytes()).toString() 
				: UUID.randomUUID().toString();
	}

}
