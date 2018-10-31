package com.prs.web;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prs.business.purchaserequest.PurchaseRequest;
import com.prs.business.purchaserequest.PurchaseRequestRepository;
import com.prs.util.JsonResponse;

@CrossOrigin
@Controller
@RequestMapping("/PurchaseRequests")
public class PurchaseRequestController {

	@Autowired
	private PurchaseRequestRepository prRepository;

	@GetMapping("/ListReview")
	public @ResponseBody JsonResponse getAllPurchaseRequestsForReview() {
		try {
			return JsonResponse.getInstance(prRepository.findAll());
		} catch (Exception e) {
			return JsonResponse.getErrorInstance("Purchase request list failure:" + e.getMessage(), e);
		}
	}

	@GetMapping("/Get/{id}")
	public @ResponseBody JsonResponse getPurchaseRequest(@PathVariable int id) {
		try {
			Optional<PurchaseRequest> purchaseRequest = prRepository.findById(id);
			if (purchaseRequest.isPresent())
				return JsonResponse.getInstance(purchaseRequest.get());
			else
				return JsonResponse.getErrorInstance("Purchase request not found for id: " + id, null);
		} catch (Exception e) {
			return JsonResponse.getErrorInstance("Error getting purchase request:  " + e.getMessage(), null);
		}
	}

	@PostMapping("/Add")
	public @ResponseBody JsonResponse addPurchaseRequest(@RequestBody PurchaseRequest pr) {
		return savePurchaseRequest(pr);
	}

	@PostMapping("/Change")
	public @ResponseBody JsonResponse updatePurchaseRequest(@RequestBody PurchaseRequest pr) {
		return savePurchaseRequest(pr);
	}

	private @ResponseBody JsonResponse savePurchaseRequest(@RequestBody PurchaseRequest pr) {
		try {
			prRepository.save(pr);
			return JsonResponse.getInstance(pr);
		} catch (DataIntegrityViolationException ex) {
			return JsonResponse.getErrorInstance(ex.getRootCause().toString(), ex);
		} catch (Exception ex) {
			return JsonResponse.getErrorInstance(ex.getMessage(), ex);
		}
	}

	@PostMapping("/SubmitForReview")
	public @ResponseBody JsonResponse submitPurchaseRequestForReview(@RequestBody PurchaseRequest pr) {
		if (pr.getTotal() <= 50) {
			pr.setStatus(PurchaseRequest.STATUS_OF_APPROVED);
		} else {
			pr.setStatus(PurchaseRequest.STATUS_OF_REVIEW);
		}
		pr.setSubmittedDate(LocalDateTime.now());
		return savePurchaseRequest(pr);
	}

	@PostMapping("/ApprovePurchaseRequest")
	public @ResponseBody JsonResponse approvePurchaseRequests(@RequestBody PurchaseRequest pr) {
		pr.setStatus(PurchaseRequest.STATUS_OF_APPROVED);
		return savePurchaseRequest(pr);
	}

	@PostMapping("/RejectPurchaseRequest")
	public @ResponseBody JsonResponse rejectPurchaseRequests(@RequestBody PurchaseRequest pr) {
		pr.setStatus(PurchaseRequest.STATUS_OF_REJECTED);
		return savePurchaseRequest(pr);
	}

	@PostMapping("/Remove")
	public @ResponseBody JsonResponse removePurchaseRequest(@RequestBody PurchaseRequest pr) {
		try {
			prRepository.delete(pr);
			return JsonResponse.getInstance(pr);
		} catch (Exception ex) {
			return JsonResponse.getErrorInstance(ex.getMessage(), ex);
		}
	}
}