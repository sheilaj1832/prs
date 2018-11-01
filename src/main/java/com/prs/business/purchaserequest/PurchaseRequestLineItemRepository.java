package com.prs.business.purchaserequest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prs.business.purchaserequest.PurchaseRequest;

public interface PurchaseRequestLineItemRepository extends JpaRepository<PurchaseRequestLineItem, Integer> {
	List<PurchaseRequestLineItem> findAllByPurchaseRequestId(int purchaseRequestId);

}
