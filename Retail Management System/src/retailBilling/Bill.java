package retailBilling;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class Bill implements Serializable
{
	private int billNo;
	private String date;
	ArrayList<Item> itemBought;
	double totalAmount;
	int memberId;
	
	
	public int getBillNo() {
		return billNo;
	}
	public String getDate() {
		return date;
	}
	public ArrayList<Item> getItemBought() {
		return itemBought;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public int getMemberId() {
		return memberId;
	}
	
	
	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setItemBought(ArrayList<Item> itemBought) {
		this.itemBought = itemBought;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	
	
	
	
}
