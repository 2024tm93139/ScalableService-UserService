package com.bits.ss.converter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.bits.ss.bean.AddressData;
import com.bits.ss.entity.Address;

@Service
public class AddressToAddressDataConverter {
	public AddressData convert(Address address) {
		if (Objects.isNull(address)) {
			return null;
		}
		AddressData data = new AddressData();
		data.setId(address.getId());
		data.setAddreddLine1(address.getAddreddLine1());
		data.setAddressLine2(address.getAddressLine2());
		data.setPinCode(address.getPinCode());
		data.setCustomer(address.getCustomer().getId());
		return data;
	}

	public List<AddressData> convertAll(List<Address> addressList) {
		if (CollectionUtils.isEmpty(addressList)) {
			return Collections.emptyList();
		}
		return addressList.stream().map(c -> this.convert(c)).collect(Collectors.toList());
	}
}
