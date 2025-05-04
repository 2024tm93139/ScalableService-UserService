package com.bits.ss.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "address_id_generator", initialValue = 20000000, allocationSize = 1)
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "address_id_generator")
	@Column(nullable = false, unique = true)
	private Integer id;
	@Column(nullable = false)
	private String addreddLine1;
	@Column
	private String addressLine2;
	@Column(nullable = false)
	private Integer pinCode;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
