package com.bits.ss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bits.ss.EntityStatus;
import com.bits.ss.bean.AddressData;
import com.bits.ss.service.AddressService;

@RestController
@RequestMapping(value = "/address")
public class AddressController {
	@Autowired
	private AddressService addressService;

	@PostMapping(value = "/create")
	public EntityStatus createAddress(@RequestBody AddressData data) {
		return addressService.createAddress(data);
	}

	@GetMapping
	public AddressData getAddressForId(@RequestParam Integer id) {
		return addressService.getAddressForId(id);
	}

	@PostMapping(value = "/update")
	public EntityStatus updateAddress(@RequestBody AddressData data) {
		return addressService.updateAddress(data);
	}

	@DeleteMapping(value = "/remove")
	public EntityStatus removeAddress(@RequestBody AddressData data) {
		return addressService.deleteAddress(data.getId());
	}

}
