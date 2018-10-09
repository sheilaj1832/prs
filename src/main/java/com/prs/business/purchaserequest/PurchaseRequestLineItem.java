package com.prs.business.purchaserequest;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.prs.business.product.Product;
import com.prs.business.vendor.Vendor;

@Entity
public class PurchaseRequestLineItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne  //many purchase requests can be provided by one user // based off primary keys in the database.
	@JoinColumn(name = "purchaseRequestID")	
	private PurchaseRequest purchaseRequest;
	
	@ManyToOne  //many purchase requests can be provided by one user // based off primary keys in the database.
	@JoinColumn(name = "productID")	
	private Product product;

	private int quantity;

	public PurchaseRequestLineItem(int id, PurchaseRequest purchaseRequest, Product product, 
			int quantity) {
		super();
		this.id = id;
		this.purchaseRequest = purchaseRequest;
		this.product = product;
		this.quantity = quantity;
	}
	public PurchaseRequestLineItem(PurchaseRequest purchaseRequest, Product product,
			int quantity) {
		super();
		this.purchaseRequest = purchaseRequest;
		this.product = product;
		this.quantity = quantity;
	}
	
	public PurchaseRequestLineItem() {
		super();
	}
	public PurchaseRequestLineItem(Vendor vendor, Product product2, int i) {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public PurchaseRequest getPurchaseRequest() {
		return purchaseRequest;
	}
	public void setPurchaseRequest(PurchaseRequest purchaseRequest) {
		this.purchaseRequest = purchaseRequest;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}