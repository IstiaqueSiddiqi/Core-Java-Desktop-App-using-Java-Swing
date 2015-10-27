package retailBilling;

import java.io.Serializable;

public class Member implements Serializable
{
	String name,address;
	int id;
	long phno;
	
    public Member(int id, String name, String address, long phno) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phno = phno;
	}
    
    //default constructor
    public Member()
    {
    	this.id = 0;
		this.name = "";
		this.address = "";
		this.phno = 0;
    }

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhno(long phno) {
		this.phno = phno;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public long getPhno() {
		return phno;
	}
	
}