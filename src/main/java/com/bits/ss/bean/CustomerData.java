package com.bits.ss.bean;

import java.util.List;

public class CustomerData {
	private Integer id;
	private String userEmail;
	private String name;
	private String phoneNumber;
	private List<AddressData> addressList;
	private List<WishlistData> wishlist;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<AddressData> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<AddressData> addressList) {
		this.addressList = addressList;
	}

	public List<WishlistData> getWishlist() {
		return wishlist;
	}

	public void setWishlist(List<WishlistData> wishlist) {
		this.wishlist = wishlist;
	}
}
