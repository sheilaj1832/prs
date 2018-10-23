package com.prs.business.purchaserequest;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prs.business.purchaserequest.PurchaseRequest;
import com.prs.business.user.User;

public interface PurchaseRequestRepository extends CrudRepository<PurchaseRequest, Integer> {
	List<PurchaseRequest> findAllByUserIdNotAndStatus(int id, String status);

}