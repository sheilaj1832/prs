package com.prs.business.purchaserequest;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prs.business.purchaserequest.PurchaseRequest;

public interface PurchaseRequestLineItemRepository extends CrudRepository<PurchaseRequestLineItem, Integer> {
	List<PurchaseRequestLineItem> findAllByPurchaseRequestId(int id);

}