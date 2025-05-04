package com.bits.ss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bits.ss.EntityStatus;
import com.bits.ss.bean.WishlistData;
import com.bits.ss.service.WishlistService;

@RestController
@RequestMapping(value = "/wishlist")
public class WishlistController {
	@Autowired
	private WishlistService wishlistService;

	@PostMapping(value = "/create")
	public EntityStatus createWishlist(@RequestBody WishlistData data) {
		return wishlistService.createWishlist(data);
	}

	@PostMapping(value = "/update")
	public EntityStatus updateWishlist(@RequestBody WishlistData data) {
		return wishlistService.updateWishlist(data);
	}

	@DeleteMapping(value = "/delete")
	public EntityStatus deleteWishlist(@RequestBody WishlistData data) {
		return wishlistService.deleteWishlist(data);
	}
}
