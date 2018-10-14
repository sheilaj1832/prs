package com.prs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.prs.business.product.Product;
import com.prs.business.product.ProductRepository;
import com.prs.business.purchaserequest.PurchaseRequest;
import com.prs.business.purchaserequest.PurchaseRequestLineItem;
import com.prs.business.purchaserequest.PurchaseRequestLineItemRepository;
import com.prs.business.purchaserequest.PurchaseRequestRepository;
import com.prs.business.user.User;
import com.prs.business.vendor.Vendor;
import com.prs.business.vendor.VendorRepository;

@RunWith(SpringRunner.class)
public class PRSPurchaseRequestLineItemTests extends PrsWebApplicationTests{
	
	@Autowired
	private PurchaseRequestLineItemRepository purchaseRequestLineItemRepository;
	
	@Autowired
	private PurchaseRequestRepository purchaseRequestRepository;

	@Autowired
	private ProductRepository productRepository;

	
	@Test
	public void testPurchaseRequestLineItemCrudFunctions () {
		//Get all purchase request line items
		Iterable<PurchaseRequestLineItem> purchaseRequestLineItems = purchaseRequestLineItemRepository.findAll();
		assertNotNull(purchaseRequestLineItems);
		
		//Add a purchase request line item
		
		Optional <PurchaseRequest> pr = purchaseRequestRepository.findById(1001);
		Optional <Product> p = productRepository.findById(7);	
		PurchaseRequestLineItem prli1  = new PurchaseRequestLineItem (pr.get(), p.get(), 5);
		assertNotNull(purchaseRequestLineItemRepository.save(prli1));
		int id = prli1.getId();
		
		//Get user & validate userName is correct
//		Optional<PurchaseRequestLineItem> prli2=purchaseRequestLineItemRepository.findById(id);
//		assertEquals(prli2.get().getUserName(),"userName");
		
		//Update the purchase request line items
//		prli2.get().setUserName("newUserName");
//		assertNotNull(purchaseRequestLineItemRepository.save(prli2.get()));
		
		//Remove the purchase request line items
//		purchaseRequestLineItemRepository.delete(prli2.get());
//		assertThat(!(purchaseRequestLineItemRepository.findById(id)).isPresent());
	}
}
