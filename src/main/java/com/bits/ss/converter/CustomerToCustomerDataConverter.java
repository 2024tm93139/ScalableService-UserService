package com.bits.ss.converter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bits.ss.bean.CustomerData;
import com.bits.ss.entity.Customer;

@Service
public class CustomerToCustomerDataConverter {
	@Autowired
	private AddressToAddressDataConverter addressToAddressDataConverter;
	@Autowired
	private WishlistToWishlistDataConverter wishlistToWishlistDataConverter;

	public CustomerData convert(Customer customer) {
		if (Objects.isNull(customer)) {
			return null;
		}
		CustomerData data = new CustomerData();
		data.setId(customer.getId());
		data.setName(customer.getName());
		data.setPhoneNumber(customer.getPhoneNumber());
		data.setUserEmail(customer.getEmail());
		if (CollectionUtils.isNotEmpty(customer.getAddressList())) {
			data.setAddressList(addressToAddressDataConverter.convertAll(customer.getAddressList()));
		}
		if (CollectionUtils.isNotEmpty(customer.getWishlist())) {
			data.setWishlist(wishlistToWishlistDataConverter.convertAll(customer.getWishlist()));
		}
		return data;
	}

	public List<CustomerData> convertAll(List<Customer> customers) {
		if (CollectionUtils.isEmpty(customers)) {
			return Collections.emptyList();
		}
		return customers.stream().map(c -> this.convert(c)).collect(Collectors.toList());
	}
}
