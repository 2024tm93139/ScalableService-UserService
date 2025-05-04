package com.bits.ss.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.bits.ss.EntityStatus;
import com.bits.ss.bean.AddressData;
import com.bits.ss.converter.AddressToAddressDataConverter;
import com.bits.ss.entity.Address;
import com.bits.ss.entity.Customer;
import com.bits.ss.entity.repository.AddressRepository;
import com.bits.ss.entity.repository.CustomerRepository;

@Service
public class AddressService {
	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressToAddressDataConverter addressToAddressDataConverter;

	public AddressData getAddressForId(Integer id) {
		Optional<Address> optionalAddress = addressRepository.findById(id);
		if (optionalAddress.isPresent()) {
			return addressToAddressDataConverter.convert(optionalAddress.get());
		}
		return null;
	}

	public EntityStatus createAddress(AddressData data) {
		Assert.notNull(data, "Address can not be null");
		Optional<Customer> customer = customerRepository.findById(data.getCustomer());
		if (!customer.isPresent()) {
			return EntityStatus.NOT_EXIST;
		}
		Address address = new Address();
		address.setAddreddLine1(data.getAddreddLine1());
		address.setAddressLine2(data.getAddressLine2());
		address.setCustomer(customer.get());
		address.setPinCode(data.getPinCode());
		addressRepository.save(address);
		return EntityStatus.CREATED;
	}

	public EntityStatus updateAddress(AddressData data) {
		Optional<Address> optionalAddress = addressRepository.findById(data.getId());
		boolean updateFlag = false;
		if (optionalAddress.isPresent()) {
			Address address = optionalAddress.get();
			if (StringUtils.isNotBlank(data.getAddreddLine1())) {
				address.setAddreddLine1(data.getAddreddLine1());
				updateFlag = true;
			}
			if (StringUtils.isNotBlank(data.getAddressLine2())) {
				address.setAddressLine2(data.getAddressLine2());
				updateFlag = true;
			}
			if (data.getPinCode() != 0) {
				address.setPinCode(data.getPinCode());
				updateFlag = true;
			}
			if (updateFlag) {
				addressRepository.save(address);
				return EntityStatus.UPDATED;
			}
			return EntityStatus.NO_CHANGE;
		}
		return EntityStatus.NOT_EXIST;
	}

	public EntityStatus deleteAddress(Integer id) {
		Optional<Address> optionalAddress = addressRepository.findById(id);
		if (optionalAddress.isPresent()) {
			addressRepository.delete(optionalAddress.get());
			return EntityStatus.DELETED;
		}
		return EntityStatus.NOT_EXIST;
	}
}
