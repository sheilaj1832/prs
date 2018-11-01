package com.prs.web;

import java.util.ArrayList;
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

import com.prs.business.product.Product;
import com.prs.business.product.ProductRepository;
import com.prs.business.purchaserequest.PurchaseRequest;
import com.prs.business.purchaserequest.PurchaseRequestLineItem;
import com.prs.business.purchaserequest.PurchaseRequestLineItemRepository;
import com.prs.business.purchaserequest.PurchaseRequestRepository;
import com.prs.util.JsonResponse;

@CrossOrigin 
@Controller
@RequestMapping("/PurchaseRequestLineItems")
public class PurchaseRequestLineItemController {
	
	@Autowired
	private PurchaseRequestLineItemRepository prliRepository;
	@Autowired
	private PurchaseRequestRepository prRepository;
	@Autowired
	private ProductRepository prodRepository;

	@GetMapping("/List")
	public @ResponseBody JsonResponse getAllPurchaseRequestLineItems() {
		try {
			return JsonResponse.getInstance(prliRepository.findAll());
		} catch (Exception e) {
			return JsonResponse.getErrorInstance("Purchase request line item list failure:" + e.getMessage(), e);
		}
	}

	@GetMapping("/Get/{id}")
public @ResponseBody JsonResponse getPurchaseRequestLineItem(@PathVariable int id) {
		try {
			Optional<PurchaseRequestLineItem> purchaseRequestLineItem = prliRepository.findById(id);
			if (purchaseRequestLineItem.isPresent())
				return JsonResponse.getInstance(purchaseRequestLineItem.get());
			else
				return JsonResponse.getErrorInstance("PurchaseRequestLineItem not found for id: "+id);
		}
		catch (Exception e) {
			return JsonResponse.getErrorInstance("Error getting purchaseRequestLineItem:  "+e.getMessage(), e);
		}
	}

	
	
	@PostMapping("/Add")
	public @ResponseBody JsonResponse addNewPurchaseRequestLineItem (@RequestBody PurchaseRequestLineItem prli) {
		JsonResponse ret = null;
		try {
			if (prli.getProduct().getName().equals("")) {
				Product prod = prodRepository.findById(prli.getProduct().getId()).get();
				prli.setProduct(prod);
			}
			ret = savePurchaseRequestLineItem(prli);			
			if (!ret.getMessage().equals(JsonResponse.SUCCESS)) {
				ret = JsonResponse.getErrorInstance("Failed to ADD prli.  Potential data corruption issue - purchaseRequestID = "+prli.getPurchaseRequest().getId());
			}
			else {
				PurchaseRequest pr = updateRequestTotal((PurchaseRequestLineItem)ret.getData());
				ret = JsonResponse.getInstance(pr);
			}
		}
		catch (Exception e) {
			String msg = "Add prli issue:  " + e.getMessage();
			e.printStackTrace();
			ret = JsonResponse.getErrorInstance(prli, msg);
		}
		return ret;
	}
	

	@PostMapping("/Change")
	public @ResponseBody JsonResponse updatePurchaseRequestLineItem(@RequestBody PurchaseRequestLineItem prli) {
		JsonResponse jres = savePurchaseRequestLineItem(prli);
		if (jres.getMessage().equals(JsonResponse.SUCCESS)) {
			try {
				updateRequestTotal(prli);
				return jres;
			} catch (Exception ex) {
				return JsonResponse.getErrorInstance(ex.getMessage(), ex);
			}
		} else {
			return jres;
		}
	}	
	@PostMapping("/Remove")
	public @ResponseBody JsonResponse removePurchaseRequestLineItem(@RequestBody PurchaseRequestLineItem prli) {
		try {
			prliRepository.delete(prli);
			updateRequestTotal(prli);
			return JsonResponse.getInstance(prli);
		} catch (Exception ex) {
			return JsonResponse.getErrorInstance(ex.getMessage(), ex);
		}
	}
	private @ResponseBody JsonResponse savePurchaseRequestLineItem(@RequestBody PurchaseRequestLineItem prli) {
		try {
			prliRepository.save(prli);
			return JsonResponse.getInstance(prli);
		} catch (DataIntegrityViolationException ex) {
			return JsonResponse.getErrorInstance(ex.getRootCause().toString(), ex);
		} catch (Exception ex) {
			return JsonResponse.getErrorInstance(ex.getMessage(), ex);
		}
	}


	private PurchaseRequest updateRequestTotal(PurchaseRequestLineItem prli) throws Exception {
		Optional<PurchaseRequest> optPR = prRepository.findById(prli.getPurchaseRequest().getId());		
		PurchaseRequest pr = optPR.get();
		List<PurchaseRequestLineItem> lines = new ArrayList<>();
		lines = prliRepository.findAllByPurchaseRequestId(pr.getId());
		double total = 0;
		for (PurchaseRequestLineItem line: lines) {
			Product p = line.getProduct();
			double lineTotal = line.getQuantity()*p.getPrice();
			total += lineTotal;
		}
		pr.setTotal(total);
		return prRepository.save(pr);	
	}
}

