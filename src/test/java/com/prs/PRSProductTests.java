//package com.prs;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//import java.util.Optional;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.prs.business.product.Product;
//import com.prs.business.product.ProductRepository;
//
//@RunWith(SpringRunner.class)
//public class PRSProductTests extends PrsWebApplicationTests{
	
//	@Autowired
//	private ProductRepository productRepository;
//	
//	//will need to have a vendor to have the product add correctly. getter from vendor repository.
//	@Test
//	public void testProductCrudFunctions () {
//		//Get all products
//		Iterable<Product> products = productRepository.findAll();
//		assertNotNull(products);
//		
//		//Add a product
//		Product u1 = new Product ("productName", "password", "firstName", "lastName", "phoneNumber", "email", true, true);
//		assertNotNull(productRepository.save(u1));
//		int id = u1.getId();
//		
//		//Get product & validate product is correct
//		Optional<Product> u2=productRepository.findById(id);
//		assertEquals(u2.get().getProduct(),"product");
//		
//		//Update the product
//		u2.get().setProductName("newProductName");
//		assertNotNull(productRepository.save(u2.get()));
//		
//		//Remove the product
//		productRepository.delete(u2.get());
//		assertThat(!(productRepository.findById(id)).isPresent());
//	}
//
//}
