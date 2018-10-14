//package com.prs;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.prs.business.product.Product;
//import com.prs.business.purchaserequest.PurchaseRequest;
//import com.prs.business.purchaserequest.PurchaseRequestRepository;
//import com.prs.business.user.User;
//import com.prs.business.user.UserRepository;
//import com.prs.business.vendor.Vendor;
//
//@RunWith(SpringRunner.class)
//public class PRSPurchaseRequestTests extends PrsWebApplicationTests{
//	
//	@Autowired
//	private PurchaseRequestRepository purchaseRequestRepository;
//	
//	@Autowired
//	private UserRepository userRepository;
//
//	
//	@Test
//	public void testPurchaseRequestCrudFunctions () {
//		//Get all purchaseRequests
////		Iterable<PurchaseRequest> purchaseRequests = purchaseRequestRepository.findAll();
////		assertNotNull(purchaseRequests);
////		
////		//Add a purchaseRequest
////		Optional <User> u = userRepository.findById(3);
////		PurchaseRequest pr1 = new PurchaseRequest(u.get(),"description", "justification", "2018-10-31", "USPS", "open", 100.00,"2018-10-08 10:00","NA");
////		assertNotNull(purchaseRequestRepository.save(pr1));
////		int id = pr1.getId();
////		
////		//Get user & validate userName is correct
////		Optional<PurchaseRequest> p2=purchaseRequestRepository.findById(id);
////		assertEquals(pr2.get().getUserName(),"userName");
////		
////		//Update the purchaseRequest
////		p2.get().setUserName("newUserName");
////		assertNotNull(purchaseRequestRepository.save(p2.get()));
////		
////		//Remove the purchaseRequest
////		purchaseRequestRepository.delete(p2.get());
////		assertThat(!(purchaseRequestRepository.findById(id)).isPresent());
////	}
////}
