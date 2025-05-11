package com.bits.ss.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bits.ss.EntityStatus;
import com.bits.ss.bean.WishlistData;
import com.bits.ss.entity.Customer;
import com.bits.ss.entity.Wishlist;
import com.bits.ss.entity.repository.CustomerRepository;
import com.bits.ss.entity.repository.WishlistRepository;

@Service
public class WishlistService {
	@Autowired
	private WishlistRepository wishlistRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public EntityStatus createWishlist(WishlistData data) {
		Optional<Customer> customer = customerRepository.findById(data.getCustomer());
		if (!customer.isPresent()) {
			return EntityStatus.NOT_EXIST;
		}
		Wishlist wishlist = new Wishlist();
		wishlist.setCustomer(customer.get());
		if (CollectionUtils.isNotEmpty(data.getProductList())) {
			wishlist.setProductList(data.getProductList());
		}
		wishlistRepository.save(wishlist);
		return EntityStatus.CREATED;
	}

	public EntityStatus updateWishlist(WishlistData data, boolean addProduct) {
		Optional<Customer> customer = customerRepository.findById(data.getCustomer());
		if (!customer.isPresent()) {
			return EntityStatus.NOT_EXIST;
		}
		Optional<Wishlist> wishlist = wishlistRepository.findById(data.getId());
		if (!wishlist.isPresent()) {
			return EntityStatus.NOT_EXIST;
		}
		List<String> existingProduct = wishlist.get().getProductList();
		if(existingProduct==null){
			existingProduct=new ArrayList()<>;
		}
		if (addProduct) {
			data.getProductList().forEach(c -> {
				if (!existingProduct.contains(c)) {
					existingProduct.add(c);
				}
			});
		} else {
			existingProduct.removeAll(data.getProductList());
		}
		wishlist.get().setProductList(existingProduct);
		wishlistRepository.save(wishlist.get());
		return EntityStatus.UPDATED;
	}

	public EntityStatus deleteWishlist(WishlistData data) {
		Optional<Wishlist> wishlist = wishlistRepository.findById(data.getId());
		if (!wishlist.isPresent()) {
			return EntityStatus.NOT_EXIST;
		}
		wishlistRepository.delete(wishlist.get());
		return EntityStatus.DELETED;
	}
}
