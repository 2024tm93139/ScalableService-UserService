package com.bits.ss.controller;

import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bits.ss.EntityStatus;
import com.bits.ss.bean.CustomerData;
import com.bits.ss.entity.Customer;
import com.bits.ss.service.CustomerService;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@PostMapping(value = "/create")
	public EntityStatus createCustomer(@RequestBody CustomerData body, @RequestHeader String password) {
		return customerService.createCustomer(body, password);
	}

	@GetMapping
	public ResponseEntity<CustomerData> getCustomer(@RequestParam Integer id) {
		CustomerData customer = customerService.getCustomer(id);
		if (Objects.isNull(customer)) {
			return new ResponseEntity<CustomerData>(customer, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CustomerData>(customer, HttpStatus.OK);
	}

	@PostMapping(value = "/update")
	public EntityStatus updateCustomer(@RequestBody CustomerData body) {
		return customerService.updateCustomer(body);
	}

	@PostMapping(value = "/credential")
	public boolean updatePassword(@RequestBody CustomerData body, @RequestHeader String oldPassword,
			@RequestHeader String newPassword) {
		return customerService.updatePassword(body.getId(), oldPassword, newPassword);
	}

	@PostMapping(value = "/login")
	public boolean loginCustomer(@RequestHeader String username, @RequestHeader String password) {
		return customerService.login(username, password);
	}

	@PostMapping(value = "/delete")
	public EntityStatus deleteUser(@RequestBody Map<String, String> body) {
		if (body == null || body.isEmpty()) {
			return EntityStatus.INCORRECT_DATA;
		}
		String username = body.get("username");
		if (StringUtils.isBlank(username)) {
			return EntityStatus.INCORRECT_DATA;
		}
		Customer customer = customerService.findByEmail(username);
		if (Objects.isNull(customer)) {
			return EntityStatus.NOT_EXIST;
		}
		customerService.deletUser(customer);
		return EntityStatus.DELETED;
	}
}
