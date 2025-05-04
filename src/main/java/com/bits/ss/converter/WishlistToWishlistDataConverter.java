package com.bits.ss.converter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.bits.ss.bean.WishlistData;
import com.bits.ss.entity.Wishlist;

@Service
public class WishlistToWishlistDataConverter {
	public WishlistData convert(Wishlist wishlist) {
		if (Objects.isNull(wishlist)) {
			return null;
		}
		WishlistData data = new WishlistData();
		data.setId(wishlist.getId());
		data.setProductList(wishlist.getProductList());
		data.setCustomer(wishlist.getCustomer().getId());
		return data;
	}

	public List<WishlistData> convertAll(List<Wishlist> wishLists) {
		if (CollectionUtils.isEmpty(wishLists)) {
			return Collections.emptyList();
		}
		return wishLists.stream().map(c -> this.convert(c)).collect(Collectors.toList());
	}
}
