package com.prs.business.vendor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface VendorRepository extends JpaRepository<Vendor, Integer>{

}
