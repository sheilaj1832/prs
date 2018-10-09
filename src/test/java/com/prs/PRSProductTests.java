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
import com.prs.business.vendor.Vendor;
import com.prs.business.vendor.VendorRepository;

@RunWith(SpringRunner.class)
public class PRSProductTests extends PrsWebApplicationTests{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private VendorRepository vendorRepository;
	
	//Will need to have a vendor to have the product add correctly. Getter from vendor repository.

	@Test
	public void testProductCrudFunctions () {
		//Get all products
		Iterable<Product> products = productRepository.findAll();
		assertNotNull(products);
		
		//Add a product	
		Optional <Vendor> v = vendorRepository.findById(3);
		Product p1 = new Product (v.get(), "partnumber", "name", 100.00, "unit", "photopath");
		assertNotNull(productRepository.save(p1));
		int id = p1.getId();
		
		//Get product & validate product is correct
		Optional<Product> p2=productRepository.findById(id);
		assertEquals(p2.get().getName(),"product");
		
		//Update the product
		p2.get().setName("newProductName");
		assertNotNull(productRepository.save(p2.get()));
		
		//Remove the product
		productRepository.delete(p2.get());
		assertThat(!(productRepository.findById(id)).isPresent());
	}

}
