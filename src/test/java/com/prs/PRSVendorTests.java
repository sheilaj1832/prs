package com.prs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.prs.business.user.User;
import com.prs.business.vendor.Vendor;
import com.prs.business.vendor.VendorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PRSVendorTests extends PrsWebApplicationTests {

	@Autowired
	private VendorRepository vendorRepository;

	@Test
	public void testVendorCrudFunctions() {

		// Get all vendors
		Iterable<Vendor> vendors = vendorRepository.findAll();
		assertNotNull(vendors);

		// Add a vendor
		Vendor v1 = new Vendor("code", "name", "address", "city", "st", "zip", "phoneNumber", "email", true);
		assertNotNull(vendorRepository.save(v1));
		int id = v1.getId();

		// Get vendor & validate name is correct
		Optional<Vendor> v2 = vendorRepository.findById(id);
		assertEquals(v2.get().getName(), "name");

		// Update the vendor
		v2.get().setName("newName");
		assertNotNull(vendorRepository.save(v2.get()));

		// Remove the vendor
		vendorRepository.delete(v2.get());
		assertThat(!(vendorRepository.findById(id)).isPresent());

	}
}
