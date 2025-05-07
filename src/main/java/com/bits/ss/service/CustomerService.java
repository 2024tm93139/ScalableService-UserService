package com.bits.ss.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.bits.ss.EntityStatus;
import com.bits.ss.bean.CustomerData;
import com.bits.ss.converter.CustomerToCustomerDataConverter;
import com.bits.ss.entity.Customer;
import com.bits.ss.entity.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerToCustomerDataConverter customerToCustomerDataConverter;

	public EntityStatus createCustomer(CustomerData data, String password) {
		Customer customer = this.findByEmail(data.getUserEmail());
		if (Objects.nonNull(customer)) {
			return EntityStatus.ALREADY_EXIST;
		}
		Customer model = new Customer();
		model.setName(data.getName());
		model.setPhoneNumber(data.getPhoneNumber());
		model.setEmail(data.getUserEmail());
		model.setPassword(encryptPassword(password));
		customerRepository.save(model);
		return EntityStatus.CREATED;
	}

	public Customer findByEmail(String email) {
		return customerRepository.findByEmail(email);
	}

	public EntityStatus updateCustomer(CustomerData data) {
		Optional<Customer> customer = customerRepository.findById(data.getId());
		if (!customer.isPresent()) {
			return EntityStatus.NOT_EXIST;
		}
		boolean updated = false;
		Customer model = customer.get();
		if (!data.getName().equals(model.getName())) {
			model.setName(data.getName());
			updated = true;
		}
		if (!data.getPhoneNumber().equals(model.getPhoneNumber())) {
			model.setPhoneNumber(data.getPhoneNumber());
			updated = true;
		}
		if (!data.getUserEmail().equals(model.getEmail())) {
			model.setEmail(data.getUserEmail());
			updated = true;
		}
		if (updated) {
			customerRepository.save(model);
			return EntityStatus.UPDATED;
		}
		return EntityStatus.NO_CHANGE;
	}

	public boolean updatePassword(Integer userId, String oldPassword, String newPassword) {
		Optional<Customer> customer = customerRepository.findById(userId);
		if (!customer.isPresent()) {
			return false;
		}
		Customer model = customer.get();
		if (this.verifyUser(model, oldPassword)) {
			model.setPassword(encryptPassword(newPassword));
			customerRepository.save(model);
			return true;
		}
		return false;
	}

	public CustomerData getCustomer(Integer id) {
		Optional<Customer> cust = customerRepository.findById(id);
		if (cust.isPresent()) {
			return customerToCustomerDataConverter.convert(cust.get());
		}
		return null;
	}

	public boolean verifyUser(Customer customer, String password) {
		return this.encryptPassword(password).equals(customer.getPassword());
	}

	public boolean verifyUser(Integer userId, String password) {
		Optional<Customer> customer = customerRepository.findById(userId);
		if (!customer.isPresent()) {
			return false;
		}
		return this.verifyUser(customer.get(), password);
	}

	private String encryptPassword(String password) {
		return DigestUtils.md5DigestAsHex(password.getBytes());
	}

	public boolean login(String username, String password) {
		Customer customer = customerRepository.findByEmail(username);
		if (Objects.isNull(customer)) {
			return Boolean.FALSE;
		} else if (!customer.getPassword().equals(encryptPassword(password))) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	public void deletUser(Customer customer) {
		customerRepository.delete(customer);
	}
}
