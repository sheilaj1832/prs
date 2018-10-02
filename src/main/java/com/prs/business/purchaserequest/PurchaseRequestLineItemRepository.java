package com.prs.business.purchaserequest;

import org.springframework.data.repository.CrudRepository;

import com.prs.business.purchaserequest.PurchaseRequest;

public interface PurchaseRequestLineItemRepository extends CrudRepository<PurchaseRequestLineItem, Integer> {

}