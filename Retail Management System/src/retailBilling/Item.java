package retailBilling;

import java.io.Serializable;

public class Item implements Serializable 
{
	//item related variables
	private int itemCode;
	private String itemName;
	private double price;
	private int quantity;
	private String productName;
	
	
	//parameterized constructor
	public Item(int itemCode, String productName, String itemName,double price, int quantity)
	{
		this.itemCode = itemCode;
		this.productName = productName;
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}
	
	//default constructor
	public Item()
	{
		this.itemCode = 0;
		this.itemName = "";
		this.price = 0.0;
		this.productName = "";
		this.quantity = 0;
	}
	
	public int getItemCode() {
		return itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public double getPrice() {
		return price;
	}
	public int getQuantity() {
		return quantity;
	}
	public String getProductName() {
		return productName;
	}
	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}	
}
