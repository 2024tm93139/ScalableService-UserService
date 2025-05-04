package com.bits.ss.bean;

public class AddressData {
	private Integer id;
	private String addreddLine1;
	private String addressLine2;
	private Integer pinCode;
	private Integer customer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddreddLine1() {
		return addreddLine1;
	}

	public void setAddreddLine1(String addreddLine1) {
		this.addreddLine1 = addreddLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public Integer getPinCode() {
		return pinCode;
	}

	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}

	public Integer getCustomer() {
		return customer;
	}

	public void setCustomer(Integer customer) {
		this.customer = customer;
	}

}
