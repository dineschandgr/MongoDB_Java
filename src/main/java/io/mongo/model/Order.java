package io.mongo.model;

public class Order {

	public int orderId;
	public int quantity;
	public int price;
	public String description;
	
	public Order(int orderId, int quantity, int price, String description) {
		super();
		this.orderId = orderId;
		this.quantity = quantity;
		this.price = price;
		this.description = description;
	}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
