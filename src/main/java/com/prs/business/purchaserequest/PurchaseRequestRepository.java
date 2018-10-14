package com.prs.business.purchaserequest;

import org.springframework.data.repository.CrudRepository;

import com.prs.business.purchaserequest.PurchaseRequest;
import com.prs.business.user.User;

public interface PurchaseRequestRepository extends CrudRepository<PurchaseRequest, Integer> {

	PurchaseRequest findByUserAndStatusofReview(String user, String STATUS_OF_REVIEW);

}