package com.prs.web;

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
import com.prs.util.JsonResponse;

@CrossOrigin 
@Controller
@RequestMapping("/Products")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/List")
	public @ResponseBody JsonResponse getAllProducts() {
		try {
			return JsonResponse.getInstance(productRepository.findAll());
		} catch (Exception e) {
			return JsonResponse.getErrorInstance("Product list failure:" + e.getMessage(), e);
		}
	}

	@GetMapping("/Get/{id}")
	public @ResponseBody JsonResponse getProduct(@PathVariable int id) {
		try {
			Optional<Product> product = productRepository.findById(id);
			if (product.isPresent())
				return JsonResponse.getInstance(product.get());
			else
				return JsonResponse.getErrorInstance("Product not found for id: " + id, null);
		} catch (Exception e) {
			return JsonResponse.getErrorInstance("Error getting product:  " + e.getMessage(), null);
		}
	}

	@PostMapping("/Add")
	public @ResponseBody JsonResponse addProduct(@RequestBody Product product) {
		return saveProduct(product);
	}

	@PostMapping("/Change")
	public @ResponseBody JsonResponse updateProduct(@RequestBody Product product) {
		return saveProduct(product);
	}

	private @ResponseBody JsonResponse saveProduct(@RequestBody Product product) {
		try {
			productRepository.save(product);
			return JsonResponse.getInstance(product);
		} catch (DataIntegrityViolationException ex) {
			return JsonResponse.getErrorInstance(ex.getRootCause().toString(), ex);
		} catch (Exception ex) {
			return JsonResponse.getErrorInstance(ex.getMessage(), ex);
		}
	}

	@PostMapping("/Remove")
	public @ResponseBody JsonResponse removeProduct(@RequestBody Product product) {
		try {
			productRepository.delete(product);
			return JsonResponse.getInstance(product);
		} catch (Exception ex) {
			return JsonResponse.getErrorInstance(ex.getMessage(), ex);
		}
	}
}